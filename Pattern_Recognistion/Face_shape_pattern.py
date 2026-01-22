import cv2
import mediapipe as mp
import math

CAMERA_INDEX = 1

mp_face_mesh = mp.solutions.face_mesh
mp_drawing = mp.solutions.drawing_utils

face_mesh = mp_face_mesh.FaceMesh(
    max_num_faces=1,
    refine_landmarks=True,
    min_detection_confidence=0.5,
    min_tracking_confidence=0.5
)

def dist(p1, p2):
    return math.hypot(p1[0] - p2[0], p1[1] - p2[1])

cap = cv2.VideoCapture(CAMERA_INDEX)
if not cap.isOpened():
    print("Camera not opened")
    exit()

while True:
    ret, frame = cap.read()
    if not ret:
        break

    frame = cv2.flip(frame, 1)
    rgb = cv2.cvtColor(frame, cv2.COLOR_BGR2RGB)
    results = face_mesh.process(rgb)

    h, w, _ = frame.shape

    if results.multi_face_landmarks:
        for face_landmarks in results.multi_face_landmarks:

            mp_drawing.draw_landmarks(
                frame,
                face_landmarks,
                mp_face_mesh.FACEMESH_TESSELATION,
                None,
                mp_drawing.DrawingSpec(color=(200, 200, 200), thickness=1)
            )

            mp_drawing.draw_landmarks(
                frame,
                face_landmarks,
                mp_face_mesh.FACEMESH_FACE_OVAL,
                None,
                mp_drawing.DrawingSpec(color=(180, 180, 180), thickness=2)
            )

            feature_ids = [10, 152, 234, 454, 172, 397]
            pts = {}

            for idx in feature_ids:
                lm = face_landmarks.landmark[idx]
                x, y = int(lm.x * w), int(lm.y * h)
                pts[idx] = (x, y)

                cv2.circle(frame, (x, y), 2, (230, 230, 230), -1)

                cv2.putText(
                    frame,
                    f"({x},{y})",
                    (x + 5, y - 5),
                    cv2.FONT_HERSHEY_PLAIN,
                    0.8,
                    (210, 210, 210),
                    1
                )

            face_length = dist(pts[10], pts[152])
            face_width = dist(pts[234], pts[454])
            jaw_width = dist(pts[172], pts[397])

            ratio = face_length / face_width

            if ratio > 1.3:
                shape = "Oval"
            elif ratio < 1.1 and jaw_width > face_width * 0.9:
                shape = "Square"
            elif ratio < 1.1:
                shape = "Round"
            else:
                shape = "Heart"

            cv2.putText(
                frame,
                f"Face Shape: {shape}",
                (30, 40),
                cv2.FONT_HERSHEY_SIMPLEX,
                0.9,
                (230, 230, 230),
                2
            )

    cv2.imshow("Face Shape Recognition – Coordinates View", frame)

    if cv2.waitKey(1) & 0xFF in [27, ord('q')]:
        break

cap.release()
cv2.destroyAllWindows()
