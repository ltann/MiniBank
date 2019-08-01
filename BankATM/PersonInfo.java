public class PersonInfo{
    private String name;
    private String cell;
    private String address;

    public PersonInfo(){
        this.name="--------------------";
        this.cell="--------------------";
        this.address="--------------------";
    }
    public PersonInfo(String x, String y,String z){
        this.name=x;
        this.cell=y;
        this.address=z;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
}