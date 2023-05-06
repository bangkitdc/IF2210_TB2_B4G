package bnmobusinessmanagementsystem.models.customer;
public class Member extends Customer{
    private String nama;
    private String noTelp;
    private double poin;
    private boolean isActive;

    public Member(String nama, String noTelp){
        super();
        this.nama = nama;
        this.noTelp = noTelp;
        this.poin = 0;
        this.isActive = true;
    }

    public Member(String nama, String noTelp, String id){
        super(id);
        this.nama = nama;
        this.noTelp = noTelp;
        this.poin = 0;
        this.isActive = true;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public double getPoin() {
        return poin;
    }

    public void addPoin(double price){
        this.poin += price*0.01;
    }

    public void setPoin(double poin) {
        this.poin = poin;
    }

    public boolean isActive() {
        return isActive;
    }

    public void statusOff() {
        this.isActive = false;
    }

    public void statusOn() {
        this.isActive = true;
    }

    public double usePoin(double price){
        double tempPrice=0;
        if(this.poin>price){
            this.poin-=price;
            return tempPrice;
        }else if(this.poin==price){
            this.poin = 0;
            return tempPrice;
        }else{
            tempPrice=price;
            tempPrice-=this.poin;
            this.poin-=price;
            return tempPrice;
        }
    }

}

//try {
//        // Membaca file JSON
//        FileReader reader = new FileReader(filename);
//        JSONParser parser = new JSONParser();
//        JSONArray customersArray = (JSONArray) parser.parse(reader);
//        int len = customersArray.toArray().length;
//        reader.close();
//
//        // Membuat objek JSON dari data customer
//        JSONObject customerObject = new JSONObject();
//
//
//        if (customer instanceof Member member) {
//        customerObject.put("tipe", "member");
//        customerObject.put("idCustomer", member.getCustomerId() + len);
//        customerObject.put("nama", member.getNama());
//        customerObject.put("noTelp", member.getNoTelp());
//        customerObject.put("poin", member.getPoin());
//        customerObject.put("isActive", member.isActive());
//        } else if (customer instanceof VIP vip) {
//        customerObject.put("tipe", "vip");
//        customerObject.put("idCustomer", vip.getCustomerId() + len);
//        customerObject.put("nama", vip.getNama());
//        customerObject.put("noTelp", vip.getNoTelp());
//        customerObject.put("poin", vip.getPoin());
//        customerObject.put("isActive", vip.isActive());
//        } else {
//        customerObject.put("tipe", "customer");
//        customerObject.put("id", customer.getCustomerId());
//        }
//
//        // Menambahkan objek JSON baru ke dalam array JSON
//        customersArray.add(customerObject);
//
//        // Menulis kembali file JSON dengan objek baru yang telah ditambahkan
//        FileWriter writer = new FileWriter(filename);
//        writer.write(customersArray.toJSONString());
//        writer.flush();
//        writer.close();
//        } catch (Exception e) {
//        e.printStackTrace();
//        }