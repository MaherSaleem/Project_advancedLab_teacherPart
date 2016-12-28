package objects;

public class tests {
	public static void main(String[] args) {
		int seconds = 55555;
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int sec = seconds % 60;
        String time = String.format("%d:%02d:%02d", hours, minutes, sec);
        System.out.println(time);

	}

}
