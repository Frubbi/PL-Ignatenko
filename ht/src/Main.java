public class Main {
    public static void main(String[] args) {

        Table table = new HashTable();
        table.put("1", "one");
        table.put("2", "two");
        table.put("3", "three");
        table.put("4", "four");
        table.put("5", "five");
        table.put("6", "six");

        System.out.println(table.get("1"));

        table.remove("1");

        System.out.println(table.get("1"));
    }
}
