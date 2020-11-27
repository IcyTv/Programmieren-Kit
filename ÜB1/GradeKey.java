public class GradeKey {
    public static void main(String[] args) {
        int grade = 17;
        switch (grade) {
            case 10:
                System.out.println("38 40.0");
                break;
            case 13:
                System.out.println("36 37.4");
                break;
            case 17:
                System.out.println("34 35.5");
                break;
            case 20:
                System.out.println("32 33.5");
                break;
            case 23:
                System.out.println("30 31.5");
                break;
            case 27:
                System.out.println("28 29.4");
                break;
            case 30:
                System.out.println("26 27.5");
                break;
            case 33:
                System.out.println("24 25.5");
                break;
            case 37:
                System.out.println("22 23.5");
                break;
            case 40:
                System.out.println("20 21.5");
                break;
            case 50:
                System.out.println("00 19.5");
                break;
            default:
                System.out.println("Exception grade not applicable");
                break;
        }
    }
}
