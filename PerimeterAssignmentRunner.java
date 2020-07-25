package perimeter_quiz;

import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints(Shape s) {
        int numPoints = 0;
        for(Point countPt : s.getPoints()) {
            numPoints += 1;
        }
        return numPoints;
    }

    public double getAverageLength(Shape s) {
        double avgLength = 0.0;
        double length = getPerimeter(s);
        int numSides = getNumPoints(s);
        avgLength = length / numSides;
        return avgLength;
    }

    public double getLargestSide(Shape s) {
        double longestSide = 0.0;
        Point prevPt = s.getLastPoint();
        Point oldPrevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            double oldDist = oldPrevPt.distance(currPt);
            double currDist = prevPt.distance(currPt);
            prevPt = currPt;
            if(currDist > oldDist) {
                longestSide = currDist;
            } else {
                longestSide = oldDist;
            }
        }  
        return longestSide;
    }

    public double getLargestX(Shape s) {
        double biggestX = 0.0;
        for(Point currPt : s.getPoints()) {
            int xCoord = currPt.getX(); 
            if(xCoord > biggestX) {
                biggestX = xCoord;
            } 
        }
        return biggestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        DirectoryResource dr = new DirectoryResource();
        double longestPerim = 0.0;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape fileS = new Shape(fr);
            double fileSPerim = getPerimeter(fileS);
            if(fileSPerim > longestPerim) {
                longestPerim = fileSPerim;
            } 
        }
        return longestPerim;
    }

    public File getFileWithLargestPerimeter() {
        DirectoryResource dr = new DirectoryResource();
        String filename = null;
        File longFile = null;
        double largestPerim = 0.0;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape fileS = new Shape(fr);
            double fileSPerim = getPerimeter(fileS);
            if(fileSPerim > largestPerim) {
                largestPerim = fileSPerim;
                longFile = f;
            } else {
              
            }
        }
        return longFile; 
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        int numPoints = getNumPoints(s);
        System.out.println("Number of points = " + numPoints);
        double avgLength = getAverageLength(s);
        System.out.println("Average length of all sides = " + avgLength);
        double longestSide = getLargestSide(s);
        System.out.println("Longest side = " + longestSide);
        double largestX = getLargestX(s);
        System.out.println("Largest x value = " + largestX);
    }
    
    public void testPerimeterMultipleFiles() {
        double largestPerim = getLargestPerimeterMultipleFiles();
        System.out.println("Largest perimeter = " + largestPerim);
    }

    public void testFileWithLargestPerimeter() {
        File largestPerim = getFileWithLargestPerimeter();
        System.out.println("File with largest perimeter is " + largestPerim);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
        pr.testFileWithLargestPerimeter();
        pr.testPerimeterMultipleFiles();
    }
}
