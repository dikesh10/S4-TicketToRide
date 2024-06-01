package net;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class Client {
    private static Socket socket;
    private static BufferedReader in;

    public static void main(String[] args) {
        String serverAddress = JOptionPane.showInputDialog("Enter IP Address of the Server:");
        try {
            socket = new Socket(serverAddress, 12345);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            SwingUtilities.invokeLater(() -> {
                JFrame frame1 = createAndShowGUI();
                // JFrame frame2 = createAndShowGUI();
                receiveCoordinates(frame1);
                // receiveCoordinates(frame2);
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static JFrame createAndShowGUI() {
        JFrame frame = new JFrame("Click Game");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null); // Set layout to null for absolute positioning
        frame.setVisible(true);

        frame.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                sendCoordinates(x, y);
                addLabel(frame, x, y); // Add label immediately on click
            }
        });

        return frame;
    }

    private static void sendCoordinates(int x, int y) {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(x + "," + y);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void receiveCoordinates(JFrame frame) {
        new Thread(() -> {
            try {
                while (true) {
                    String input = in.readLine();
                    if (input != null) {
                        String[] coordinates = input.split(",");
                        int x = Integer.parseInt(coordinates[0]);
                        int y = Integer.parseInt(coordinates[1]);
                        System.out.println("Received coordinates: x=" + x + ", y=" + y);
                        SwingUtilities.invokeLater(() -> addLabel(frame, x, y));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private static void addLabel(JFrame frame, int x, int y) {
        JLabel label = new JLabel("X");
        label.setBounds(x, y, 20, 20); // Adjust size as needed
        frame.add(label);
        frame.revalidate();
        frame.repaint();
        System.out.println("Label added at x=" + x + ", y=" + y);
    }
}
