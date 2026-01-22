import cv2

# Try both backends explicitly
tests = [
    (cv2.CAP_MSMF, "MSMF"),
    (cv2.CAP_DSHOW, "DSHOW")
]

for backend, name in tests:
    print(f"\nTesting backend: {name}")
    for i in range(5):
        cap = cv2.VideoCapture(i, backend)
        if cap.isOpened():
            ret, frame = cap.read()
            if ret and frame is not None:
                print(f"✅ {name} camera index {i} WORKING")
            else:
                print(f"⚠ {name} camera index {i} opened but NO frame")
            cap.release()
        else:
            print(f"❌ {name} camera index {i} not opened")
