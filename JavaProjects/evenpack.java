import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class Demo1 extends JFrame implements ActionListener
{
JLabel l1;
JLabel l2;
JLabel l3;
JTextField t1;
JTextField t2;
JButton b1;
JButton b2;
public Demo1()
{
setLayout(new FlowLayout());
l1=new JLabel("First Number:");
t1=new JTextField(20);
l2=new JLabel("Second Number:");
t2=new JTextField(20);
b1=new JButton("Add");
b2=new JButton("Sub");
l3=new JLabel("Result");
add(l1);
add(t1);
add(l2);
add(t2);
add(b1);
add(b2);
add(l3);

b1.addActionListener(this);
b2.addActionListener(this);
setVisible(true);
setSize(250,400);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
public void actionPerformed(ActionEvent e)
{
int num1=Integer.parseInt(t1.getText()); //Get numbers from text fields:
int num2=Integer.parseInt(t2.getText());
if(e.getSource()==b1) {
int value=num1+num2;
l3.setText("Sum is"+value);}
else if(e.getSource()==b2) {
int value=num1-num2;
l3.setText("Difference is "+value);}
}
}
public class SwingAdd
{
public static void main(String args[])
{
Demo1 obj=new Demo1();
}
}