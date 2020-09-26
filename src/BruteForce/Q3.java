package BruteForce;

//카펫
public class Q3 {
    static public int[] solution(int brown, int yellow) {
        int whole = brown + yellow; //r*c. c>=r
        for (int r = 3; r * r <= whole; r++) {
            if (whole % r != 0) continue;
            int c = whole / r;
            if((c-2)*(r-2)==yellow) return new int[]{c,r};
        }
        return null;
    }

    public static void main(String[] args) {
        for (int e : solution(24, 24))
            System.out.print(e + " ");
    }
}
