package perimeter_quiz;
//"https://www.dukelearntoprogram.com//course2/files.php"
import edu.duke.*;
//"https://www.dukelearntoprogram.com/course1/doc/"
import java.io.File;


public class PerimeterAssignmentRunner {
    //calculate the total perimeter of a shape, starting with the 'last' point.
    public double getPerimeter (Shape s) {
        double totalPerim = 0.0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            double currDist = prevPt.distance(currPt);
            totalPerim = totalPerim + currDist;
            prevPt = currPt;
        }
        return totalPerim;
    }
    //calculate the number of distinct individual points in any given shape.
    public int getNumPoints(Shape s) {
        int numPoints = 0;
        for(Point countPt : s.getPoints()) {
            numPoints += 1;
        }
        return numPoints;
    }
    //Calculate the average length of all the sides of a given shape.
    public double getAverageLength(Shape s) {
        double avgLength = 0.0;
        double length = getPerimeter(s);
        int numSides = getNumPoints(s);
        avgLength = length / numSides;
        return avgLength;
    }
    //Identify the length of the longest side of a given shape.
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
    //find the largest x value of any pixel from a given shape.
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
    //return the largest total perimeter from multiple files containing multiple shapes.
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
    //return the filename containing the shape with the largest perimeter.
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
    //test all methods above.
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
    //test if the correct total perimeter is returned.
    public void testPerimeterMultipleFiles() {
        double largestPerim = getLargestPerimeterMultipleFiles();
        System.out.println("Largest perimeter = " + largestPerim);
    }
    //test if the file returned does contain the shape with the largest perimeter.
    public void testFileWithLargestPerimeter() {
        File largestPerim = getFileWithLargestPerimeter();
        System.out.println("File with largest perimeter is " + largestPerim);
    }
    //Creates a triangle that can be used to test other methods.
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = " + peri);
    }
    //Prints all of the names of files within a given folder.
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
