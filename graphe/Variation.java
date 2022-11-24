package graphe;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Variation extends JPanel {
    Method ordonnee;
    Method abscisse;
    Object[] objects;

    public void setObjects(Object[] objects) 
        throws NullPointerException {
        if (objects.length == 0) throw new NullPointerException("PAS DE DONNEES");
        this.objects = objects;
    }

    public void setAbscisse(String abscisse) 
        throws NoSuchMethodException, SecurityException {
        this.abscisse = objects[0].getClass().getMethod("get" + toUpperCasefisrtLetter(abscisse));
    }

    public void setOrdonnee(String ordonnee) 
        throws NoSuchMethodException, SecurityException {
        this.ordonnee = objects[0].getClass().getMethod("get" + toUpperCasefisrtLetter(ordonnee));
    }

    public Method getOrdonnee() {
        return ordonnee;
    }

    public Method getAbscisse() {
        return abscisse;
    }

    public Variation() {}
    
    public Variation(Object[] objects, String ordonnee, String abscisse) 
        throws NoSuchMethodException, SecurityException, NullPointerException {
        setObjects(objects);
        setOrdonnee(ordonnee);
        setAbscisse(abscisse);
    }
    
    public void paint(Graphics g) {
        try {
            createRepere(g);
            createPoint(g);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(new JFrame(), e.getMessage());
        }
    }

    public void initFrame(JFrame frame) {
        frame.add(this);
        frame.setSize(((objects.length - 1) * 100) + 200, 628);
        frame.setTitle("Graphe");
        frame.setResizable(false);
        frame.setLocation(100, 100);
        frame.setVisible(true);
    }
    
    public void createRepere(Graphics g) 
        throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        // Axe des Ordonnées
        g.drawLine(100, 80, 100, 500);
        // Axe des Abscisses
        g.drawLine(100, 500, ((objects.length - 1) * 100) + 150, 500);
        createValue(g);
    }

    public void createValue(Graphics g) 
        throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        int distanceY = 500, distanceX = 100;
        // Axe des Ordonnées
        for (int i = 0; i <= 400; i += 40) {
            g.drawString(Integer.toString((i * 1000) / (40)), 50, distanceY);
            distanceY -= 40;
        }
        // Axe des Abscisses
        for (int i = 0; i < objects.length; i++) {
            g.drawString(getAbscisse().invoke(objects[i]).toString(), distanceX - 45, 525);
            distanceX += 100;
        }
    }

    public void createPoint(Graphics g) 
        throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        g.setColor(Color.BLUE);
        for (int i = 0; i + 1 < objects.length; i++)
            g.drawLine(convertX(i), convertY((double) getOrdonnee().invoke(objects[i])), convertX(i+1), convertY((double) getOrdonnee().invoke(objects[i+1])));
    }

    public int convertY(double salaire) {
        return (int) ((628 - 28) - ((salaire * 40) / 1000)) - 100;
    }

    public int convertX(int index) {
        return (index * 100) + 100;
    }

    public static String toUpperCasefisrtLetter(String name) {
        String firstLetter = name.substring(0, 1);
        String remainingLetters = name.substring(1);
        firstLetter = firstLetter.toUpperCase();
        name = firstLetter + remainingLetters;
        return name;
    }
}