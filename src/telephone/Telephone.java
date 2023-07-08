package telephone;

import java.sql.*;
import java.awt.*;
import java.awt.event.*;

public class Telephone extends Frame implements ItemListener, ActionListener {

    Checkbox c1, c2, c3, c4;
    CheckboxGroup cbg;
    TextField tf1, tf2, tf3, tf4, tf5, tf6, pno, area, city, fname, lname, mname;
    CardLayout c = new CardLayout();

    Panel p1 = new Panel();
    Panel p2 = new Panel();
    Panel p3 = new Panel();
    Panel p4 = new Panel();
    Panel p5 = new Panel();
    Panel p6 = new Panel();
    Panel p7 = new Panel();
    Panel p8 = new Panel();
    Panel p9 = new Panel();
    Panel p10 = new Panel();
    Panel p11 = new Panel();
    Panel p12 = new Panel();
    Panel p13 = new Panel();
    Panel p14 = new Panel();
    Panel p15 = new Panel();
    Panel p22 = new Panel();
    Panel p16 = new Panel();
    Panel p17 = new Panel();
    Panel p18 = new Panel();
    Panel p19 = new Panel();
    Panel p20 = new Panel();
    Panel p21 = new Panel();

    TextArea ta = new TextArea();

    Button submit, home, enter;

    Connection cn = null;
    PreparedStatement p = null;
    ResultSet rs = null;

    Telephone() {

        super("Telephone");
        setSize(400, 400);
        setVisible(true);

        cbg = new CheckboxGroup();

        p1.setLayout(c);

        p2.setLayout(new BorderLayout());
        p2.add(new Label("Select the Method for searching"), BorderLayout.NORTH);

        submit = new Button("submit");
        home = new Button("home");

        submit.addActionListener(this);
        home.addActionListener(this);

        p14.add(home);
        p14.add(submit);

        p2.add(p12, BorderLayout.SOUTH);

        c1 = new Checkbox("Name", false, cbg);
        p3.add(c1);

        c2 = new Checkbox("Number", false, cbg);
        p3.add(c2);

        c3 = new Checkbox("Address", false, cbg);
        p3.add(c3);

        c4 = new Checkbox("Insert", false, cbg);
        p3.add(c4);

        c1.addItemListener(this);

        c2.addItemListener(this);

        c3.addItemListener(this);

        c4.addItemListener(this);

        p2.add(p3);

        p5.add(new Label("First Name :- "));
        p5.add(tf1 = new TextField(15));

        p6.add(new Label("Middle Name :- "));
        p6.add(tf2 = new TextField(15));

        p7.add(new Label("Last Name :- "));
        p7.add(tf3 = new TextField(15));

        p4.add(p5);
        p4.add(p6);
        p4.add(p7);

        p8.add(new Label("Phone Number :- "));
        p8.add(tf4 = new TextField(15));

        p10.add(new Label("Area :- "));
        p10.add(tf5 = new TextField(15));

        p11.add(new Label("City :- "));
        p11.add(tf6 = new TextField(15));

        p9.add(p10);
        p9.add(p11);

        p13.add(ta = new TextArea());

        p16.add(new Label("Enter Phone Number :- "));
        p16.add(pno = new TextField(20));

        p17.add(new Label("Enter Area :- "));
        p17.add(area = new TextField(20));

        p18.add(new Label("Enter City :- "));
        p18.add(city = new TextField(20));

        p19.add(new Label("Enter First Name :- "));
        p19.add(fname = new TextField(20));

        p20.add(new Label("Enter Middle Name :- "));
        p20.add(mname = new TextField(20));

        p21.add(new Label("Enter Last Name :- "));
        p21.add(lname = new TextField(20));

        p22.add(enter = new Button("enter"));

        p15.add(p16);
        p15.add(p17);
        p15.add(p18);
        p15.add(p19);
        p15.add(p20);
        p15.add(p21);
        p15.add(p22);

        p1.add(p2, "1");
        p1.add(p4, "2");
        p1.add(p8, "3");
        p1.add(p9, "4");
        p1.add(p15, "5");
        p1.add(p13, "6");
        add(p1);
        add(p14, BorderLayout.SOUTH);

        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            cn = DriverManager.getConnection("jdbc:derby://localhost:1527/Telephone", "Dhruvil", "Dhruvil");

        } catch (Exception e) {
            System.out.println(e);
        }
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent ep) {
                System.exit(0);
            }
        });

    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (c1.getState()) {
            c.next(p1);
        } else if (c2.getState()) {
            c.next(p1);
            c.next(p1);
        } else if (c3.getState()) {
            c.next(p1);
            c.next(p1);
            c.next(p1);
        } else if (c4.getState()) {
            c.next(p1);
            c.next(p1);
            c.next(p1);
            c.next(p1);
        }
    }

    public static void main(String[] args) {
        new Telephone();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = new String("");
        String query = new String("");
        String number = new String("");
        String address = new String("");
        String iname = new String("");
        double ipno;
        String iadd = new String("");
        try {
            query = new String("Select * from DHRUVIL.TELEPHONEDIR");
            if (e.getActionCommand().equals("submit")) {
                c.last(p1);

                if (c1.getState()) {
                    name = "" + tf1.getText().trim() + " " + tf2.getText().trim() + " " + tf3.getText().trim();
                    query += " Where NAME=?";
                    p = cn.prepareStatement(query);
                    p.setString(1, name);
                    System.out.println(query);
                    rs = p.executeQuery();

                    ta.setText("Number\t\tName\t\tAddress\n");
                    ta.append("-------------------------------------------------------------------------------------\n");
                    while (rs.next()) {
                        ta.append("" + rs.getString(1) + "\t");
                        ta.append("" + rs.getString(2) + "\t");
                        ta.append("" + rs.getString(3) + "\t\n");
                    }
                } else if (c2.getState()) {
                    try {
                        number += tf4.getText().trim();
                        query += " Where NUMBER =?";
                        p = cn.prepareStatement(query);
                        p.setString(1, number);
                        ta.setText(number);
                        System.out.println(query);
                        rs = p.executeQuery();

                        ta.setText("Number\t\tName\t\tAddress\n");
                        ta.append("-------------------------------------------------------------------------------------\n");
                        while (rs.next()) {
                            ta.append("" + rs.getString(1) + "\t");
                            ta.append("" + rs.getString(2) + "\t");
                            ta.append("" + rs.getString(3) + "\t\n");
                        }
                    } catch (Exception pl) {
                        System.out.println("hjghsd");
                    }
                } else if (c3.getState()) {
                    try {
                        address = "" + tf5.getText().trim() + " " + tf6.getText().trim();
                        query += " Where ADDRESS =?";
                        p = cn.prepareStatement(query);
                        p.setString(1, address);

//                        ta.setText(address);
                        System.out.println(query);
                        rs = p.executeQuery();

                        ta.setText("Number\t\tName\t\tAddress\n");
                        ta.append("-------------------------------------------------------------------------------------\n");
                        while (rs.next()) {
                            ta.append("" + rs.getString(1) + "\t");
                            ta.append("" + rs.getString(2) + "\t");
                            ta.append("" + rs.getString(3) + "\t\n");
                        }
                    } catch (Exception pdfsghj) {
                        System.out.println(pdfsghj);
                    }
                } else if (c4.getState()) {

                }
            } else if (e.getActionCommand().equals("enter")) {
                try {
                    iname = fname.getText().trim() + " " + mname.getText().trim() + " " + lname.getText().trim();
                    ipno = Double.parseDouble(pno.getText().trim());
                    iadd = area.getText().trim() + " " + city.getText().trim();

//                    Statement s = cn.createStatement();
//                    s.executeUpdate("INSERT INTO DHRUVIL.TELEPHONEDIR VALUES (" + ipno + "," + iname + "," + iadd + ")");
//                    c.first(p1);
                    p = cn.prepareStatement("INSERT INTO DHRUVIL.TELEPHONEDIR VALUES (?,?,?)");
                    p.setDouble(1, ipno);
                    p.setString(2, iname);
                    p.setString(3, iadd);

                    p.executeUpdate();
                    c.first(p1);

                } catch (Exception d) {
                    System.out.println(d);
                }
            } else if (e.getActionCommand().equals("home")) {
                c.first(p1);
            }
        } catch (Exception ae) {
            System.out.println(ae);
        }

    }

}
