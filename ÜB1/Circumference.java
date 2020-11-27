public class Circumference {
    public static final float PI = 3.14f;

    public static void main(String[] args) {
        float radius = 2.0f;
        System.out.println(Circumference.getCircumference(radius));
    }

    public static float getCircumference(float radius) {
        return 2.0f * PI * radius;
    }
}
