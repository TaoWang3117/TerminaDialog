public class Encryption  {

    public static void main(String[] args) {
        String text = "Hello World!!!";
        String code = new String();
        for(int i = 0; i < text.length(); i++){
            char a = text.charAt(i);
            int asc = (int) a + 80;
            code += Integer.toHexString(asc);
        }
        System.out.println(code);

        for (int j = 0; j < code.length(); j+=2) {
            int dec = Character.getNumericValue(code.charAt(j)) * 16 + Character.getNumericValue(code.charAt(j + 1));
            dec -= 80;
            System.out.print((char) dec);
        }
    }
}