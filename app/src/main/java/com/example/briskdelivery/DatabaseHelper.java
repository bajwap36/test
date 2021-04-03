package com.example.briskdelivery;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    //region Constants
    final static String DATABASE_NAME = "BriskDelivery.db";
    final static int DATABASE_VERSION = 1;
    // Change the table names and press ctrl + R
    final static String Restaurant = "Restaurant";
    final static String Dish = "Dish";
    final static String User = "User";
    final static String Orders = "Orders";
    final static String OrderItem = "OrderItem";
//    final static String ZipCode = "ZipCode";
//    final static String Invite = "Invite";

    //RESTAUTANT COLUMNS
    final static String RId = "RId";
    final static String RName = "RestName";
    final static String RPic = "RestImage";

    //DISH COLUMNS
    final static String DId = "DishId";
    final static String DRId = "RId";
    final static String DTitle = "Title";
    final static String DDesc = "Description";
    final static String Dprice = "Price";

    //USER COLUMNS
    final static String UId = "UserId";
    final static String UEmail = "Email";
    final static String UName = "Name";
    final static String UPhone = "Phone";
    final static String UAddress = "Address";
    final static String UPassword = "Password";

    //ORDER COLUMNS
    final static String OId = "OrderId";
    final static String OUId = "UserId";
    final static String ODate = "Date";
    final static String OStatus = "Status";
    final static String OType = "Type";
    final static String OTotal = "Total";
    final static String ODelivery = "Delivery";
    final static String OTax = "Tax";
    final static String OPayment = "Payment";

    //ORDERITEM COLUMNS
    final static String OIOId = "OrderId";
    final static String OIDId = "DishId";
    final static String OIQty = "Qty";
    final static String OIPrice = "OrderPrice";
    final static String OIRestriction = "Restriction";
    final static String OIRId = RId;

    //ZIP COLUMNS
//    final static String T6COL_1 = "RId";
//    final static String T6COL_2 = "ZipCode";

    //INVITE COLUMNS
//    final static String T7COL_1 = "UserId";
//    final static String T7COL_2 = "FriendEmail";
//    final static String T7COL_3 = "isAvailable";
//endregion

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Restaurant
        String rQuery = "Create Table " + Restaurant + "("+
                RId +" INTEGER PRIMARY KEY, "+
                RName +" TEXT, " +
                RPic +" TEXT )";
        //Dish
        String dQuery = "Create Table " + Dish + "("+
                DId +" INTEGER, "+
                DRId +" INTEGER, " +
                DTitle +" TEXT, " +
                DDesc +" TEXT, " +
                Dprice +" REAL)";
        //User
        String uQuery = "Create Table " + User + "("+
                UId +" INTEGER PRIMARY KEY, "+
                UEmail +" TEXT, " +
                UName +" TEXT, " +
                UPhone +" INTEGER, " +
                UAddress +" TEXT, " +
                UPassword +" TEXT )";
        //Order
        String oQuery = "Create Table " + Orders + "("+
                OId +" INTEGER PRIMARY KEY, "+
                OUId +" INTEGER, " +
                ODate +" TEXT, " +
                OStatus +" INTEGER, " +
                OType +" INTEGER, " +
                OTotal +" REAL, " +
                ODelivery +" REAL, " +
                OTax +" REAL, " +
                OPayment +" TEXT)";
        //OrderItem
        String iQuery = "Create Table " + OrderItem + "("+
                OIOId +" INTEGER, "+
                OIDId +" INTEGER, " +
                OIQty +" INTEGER, " +
                OIPrice +" REAL, " +
                OIRestriction +" TEXT," +
                OIRId + " INTEGER )";

//        //Zip
//        String zQuery = "Create Table " + ZipCode + "("+
//                T6COL_1 +" INTEGER, "+
//                T6COL_2 +" TEXT)";
//
//        //Invite
//        String vQuery = "Create Table " + Invite + "("+
//                T7COL_1 +" INTEGER, "+
//                T7COL_2 +" TEXT," +
//                T7COL_3 +" INTEGER )";

        try{
            db.execSQL(rQuery);
            db.execSQL(dQuery);
            db.execSQL(uQuery);
            db.execSQL(oQuery);
            db.execSQL(iQuery);
//            db.execSQL(zQuery);
//            db.execSQL(vQuery);

        }catch (Exception e){
            e.printStackTrace();;
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists " + Restaurant);
        db.execSQL("Drop table if exists " + Dish);
        db.execSQL("Drop table if exists " + User);
        db.execSQL("Drop table if exists " + Orders);
        db.execSQL("Drop table if exists " + OrderItem);
//        db.execSQL("Drop table if exists " + ZipCode);
        onCreate(db);
    }

    public long addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UEmail, user.getEmail());
        values.put(UName, user.getUname());
        values.put(UPhone, user.getPhone());
        values.put(UAddress, user.getAddress());
       // values.put(T3COL_7, user.getZIPCode());
        values.put(UPassword, user.getUpass());
        long r = db.insert(User, null, values);
        return r;

    }

    public int addOrders(Orders orders) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(OUId, orders.getUserId());
        //values.put(ODate, orders.getDate());
        values.put(OStatus, orders.isStatus());
        //values.put(OType, orders.isType());
        //values.put(OTotal, orders.getTotal());
        //values.put(ODelivery, orders.getDelivery());
        //values.put(OTax, orders.getTax());
        //values.put(OPayment, orders.getPayment());
        long r = db.insert(Orders, null, values);

        return (int)r;

    }

    public boolean addItem(Item item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(OIOId, item.getOrderId());
        values.put(OIDId, item.getDishId());
        values.put(OIQty, item.getQty());
        values.put(OIPrice, item.getPrice());
        //values.put(OIRestriction, item.getRestriction());
        values.put(OIRId, item.getRestId());
        long r = db.insert(OrderItem, null, values);
        if(r == -1){
            return false;
        }else{
            return true;
        }
    }

    //method to add restaurants
    public boolean addRestaurant( Restaurant restaurant) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(RName, restaurant.getRestName());
        values.put(RPic, restaurant.getRestImage());
        long r = db.insert(Restaurant, null, values);
        if(r == -1){
            return false;
        }else{
            return true;
        }
    }

    //method to add restaurants
//    public boolean addZip(int id, String zip) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(T6COL_1, id);
//        values.put(T6COL_2, zip);
//        long r = db.insert(ZipCode, null, values);
//        if(r == -1){
//            return false;
//        }else{
//            return true;
//        }
//    }

    //method to add dishes
    public boolean addDish(Dish dish) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DId, dish.getDishId());
        values.put(DRId, dish.getRestId());
        values.put(DTitle, dish.getTitle());
        values.put(DDesc, dish.getDescription());
        values.put(Dprice, dish.getPrice());
        long r = db.insert(Dish, null, values);
        if(r == -1){
            return false;
        }else{
            return true;
        }
    }

//    public boolean addInvite(int userId, String email){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(T7COL_1, userId);
//        values.put(T7COL_2, email);
//        values.put(T7COL_3, 1);
//        long r = db.insert(Invite, null, values);
//        if(r == -1){
//            return false;
//        }else{
//            return true;
//        }
//    }

//    public boolean isAvailable(int userId){
//        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
//        String query = "SELECT "+ T7COL_1 +" from " + Invite +
//                " where "+ T7COL_1 + "= "+ userId +
//                " and " + T7COL_3 + "=1";
//        Cursor c = sqLiteDatabase.rawQuery(query,null);
//        if(c.getCount() > 0){
//            return true;
//        }else{
//            return false;
//        }
//    }

//    public void setUsedDiscount(int userId){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(T7COL_3, 0);
//        long r = db.update(Invite, values, T7COL_1+"="+userId , null);
//    }

    //check user
    private Cursor checkUser(String email){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT "+ UId +" from " + User +
                " where "+ UEmail + "='" + email ;
        Cursor c = sqLiteDatabase.rawQuery(query,null);
        return c;
    }

    //check user
    public boolean checkEmailExists(String email){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT "+ UId +" from " + User +
                " where "+ UEmail + "='" + email + "'" ;
        Cursor c = sqLiteDatabase.rawQuery(query,null);

        if (c.getCount() > 0) return true;
        else    return false;

    }

    //check user
    public int checkUser(User user){
        Cursor check = checkUser(user.getEmail());
        int id = 0;
        if(check.getCount()>0){
            while (check.moveToNext()) {
                id = check.getInt(0);
            }
            return id;
        }else {
            return id;
        }
    }

    public Cursor selectUser(int userId){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT * from " + User +
                " where "+ UId + "=" + userId ;
        Cursor c = sqLiteDatabase.rawQuery(query,null);
        return c;
    }



    public User GetUser(int userId){
        Cursor c = selectUser(userId);
        User user = null;

        if(c.getCount()>0){
            while (c.moveToNext()) {
                user = new User(c.getString(0),
                        c.getString(1),
                        c.getString(2),
                        c.getString(3),
                        c.getString(4),
                        c.getInt(5));
            }
            return user;
        }else {
            return null;
        }
    }

    //select all users
    public Cursor viewUsers(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT * from " + User ;
        Cursor c = sqLiteDatabase.rawQuery(query,null);
        return c;
    }

    //retrieve restaurant based on ZIPCode
    private Cursor viewRestaurants(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
//        String query = "SELECT "+ Restaurant +".* from " + Restaurant + " inner join " +
//                        ZipCode+" on "+ Restaurant +"." + RId+ " = " + ZipCode + "." + T6COL_1+
//                        " inner join " +
//                        User+" on "+ User +"." + T3COL_7+ " = " + ZipCode + "."+ T6COL_2+
//                        " where "+ User +"."+ UId + "=" + id;

        String query = "SELECT "+ Restaurant +".* from " + Restaurant;



        Cursor c = sqLiteDatabase.rawQuery(query,null);
        //c.close();
        return c;
    }

    public ArrayList< Restaurant> GetRestautantList(){
        ArrayList<Restaurant> restlist = new ArrayList<Restaurant>();
        Cursor c = viewRestaurants();

        if(c.getCount() > 0){
            while(c.moveToNext()){
                restlist.add(new Restaurant(c.getInt(0), c.getString(1), c.getInt(2)));
            }
            return restlist;
        }
        return restlist;
    }

    //retrieve menu based on RId
    private Cursor viewMenu(int restId){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT * from " + Dish +
                " where "+ DRId + "=" + restId;
        Cursor c = sqLiteDatabase.rawQuery(query,null);
        return c;
    }

    public ArrayList<Dish> GetMenu(int restId){
        ArrayList<Dish> dishes = new ArrayList<>();
        Cursor c = viewMenu(restId);

        if(c.getCount() > 0){
            while (c.moveToNext()){
                dishes.add(new Dish(c.getInt(0), c.getInt(1), c.getString(2), c.getString(3), c.getDouble(4)));
            }
            return dishes;
        }
        return dishes;
    }

    private Cursor viewDish(int dishId, int restId){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT * from " + Dish +
                " where "+ DId + "=" + dishId + " and " + DRId + " = " + restId;
        Cursor c = sqLiteDatabase.rawQuery(query,null);
        return c;
    }

    public Cursor viewItems(int orderId){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT " + DTitle + ", " + OIQty + " FROM "+ OrderItem + " i INNER JOIN " + Dish +
                " d ON i."+OIDId +" = d." + DId + " and i." + OIRId + " = d."+ DRId+
                " where i."+ OIOId + "=" + orderId;
        Cursor c = sqLiteDatabase.rawQuery(query, null);
        return c;
    }

    public Dish GetDish(int dishId, int restId){
        Cursor c = viewDish(dishId, restId);
        Dish d;
        if(c.getCount() > 0){
            while (c.moveToNext()){
                return d = new Dish(c.getInt(0), c.getInt(1), c.getString(2), c.getString(3), c.getDouble(4));
            }
        }
        return d = null;
    }

    //retrieve orders based on UserId
    public Cursor viewOrders(int userId){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT * from " + Orders +
                " where "+ OUId + "=" + userId +
                " and " + OStatus + " = 1";
        Cursor c = sqLiteDatabase.rawQuery(query,null);
        return c;

    }

    public ArrayList<Orders> GetOrders(int userId){
        Cursor c = viewOrders(userId);
        ArrayList<Orders> orders = new ArrayList<>();

        if(c.getCount() > 0){
            while (c.moveToNext()){
                orders.add(new Orders(c.getInt(0),
                        c.getInt(1),
                        c.getString(2),
                        c.getInt(3) == 1 ? true : false,
                        c.getInt(4) == 1 ? true : false,
                        c.getDouble(5),
                        c.getDouble(6),
                        c.getDouble(7),
                        c.getString(8)));

            }
            return orders;
        }
        return orders;
    }

    public boolean updateOrder(Orders order){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(OUId, order.getUserId());
        values.put(ODate, order.getDate());
        values.put(OStatus, order.isStatus());
        values.put(OType, order.isType());
        values.put(OTotal, order.getTotal());
        values.put(ODelivery, order.getDelivery());
        values.put(OTax, order.getTax());
        values.put(OPayment, order.getPayment());

        long r = db.update(Orders,values, OId+"="+order.getOrderId(), null);
        if(r == -1){
            return false;
        }else{
            return true;
        }
    }

    public boolean updateUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UEmail, user.getEmail());

        values.put(UName, user.getUname());
        values.put(UPhone, user.getPhone());
        values.put(UAddress, user.getAddress());
      //  values.put(T3COL_7, user.getZIPCode());
        values.put(UPassword, user.getUpass());
        long r = db.update(User,values, " userId="+user.getuId(), null);
        if(r == -1){
            return false;
        }else{
            return true;
        }
    }

    public Cursor GetSubTotal(int orderId){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT i."+OIPrice+" from " +
                Orders + " o inner join " + OrderItem + " i on o."+ OId+"=i."+ OIOId+
                " WHERE o." + OId + "=" + orderId;
        Cursor c = sqLiteDatabase.rawQuery(query,null);
        return c;
    }

    public void PopulateRestaurant(){
        ArrayList< Restaurant> rests = new ArrayList<>(20);
        //change path
        rests.add(new  Restaurant("KFC" , R.drawable.subway)); //1
        rests.add(new  Restaurant("Bella Italia" , R.drawable.bellaitalia)); //2
        rests.add(new  Restaurant("Subway" , R.drawable.subway_logo));//3
        rests.add(new  Restaurant("McDonalds" , R.drawable.iceandspice));//4
        rests.add(new  Restaurant("Hayak Sushi" , R.drawable.hyacksushi));//5

        rests.add(new  Restaurant("A&W" , R.drawable.aandw));//6
        rests.add(new  Restaurant("Beer Creek" , R.drawable.beercreek));//7
        rests.add(new  Restaurant("Cha Time" , R.drawable.chatime));//8
        rests.add(new  Restaurant("Dairy Queen" , R.drawable.dairyqueen));//9
        rests.add(new  Restaurant("Donair Dude" , R.drawable.donairdude));//10
        rests.add(new  Restaurant("Fresh Slice" , R.drawable.freshslice));//11
        rests.add(new  Restaurant("Jaipur Indian" , R.drawable.jaipurindian));//12
        rests.add(new  Restaurant("Pinch Of SPice " , R.drawable.pinchofspice));//13
        rests.add(new  Restaurant("Uncle Fatih" , R.drawable.unclefatih));//14
        rests.add(new  Restaurant("7 Eleven" , R.drawable.seveneleven));//15
        rests.add(new  Restaurant("Nandos " , R.drawable.nandos));//16
        rests.add(new  Restaurant("White Spot" , R.drawable.whitespot));//17


        //loggers
        for ( Restaurant r : rests) {
            System.out.println(r.getRestName() +": "+ this.addRestaurant(r));
        }
    }

//    public void PopulateZip(){
//
//        this.addZip(1, "V3M2B8");
//        this.addZip(1, "V3V6T6");
//        this.addZip(1, "V3V8B1");
//        this.addZip(1, "V3R2B6");
//        this.addZip(1, "V3R1E1");
//
//        //ITALIA
//        this.addZip(2, "V3LOA2");
//        this.addZip(2, "V3R2B6");
//        this.addZip(2, "V3V9V3");
//        this.addZip(2, "V3V4F6");
//        this.addZip(2, "V3V9B7");
//        this.addZip(2, "V3M2B8");
//
//        //SUBWAY
//        this.addZip(3, "V3V4F6");
//        this.addZip(3, "V3V9B7");
//        this.addZip(3, "V3V8B1");
//        this.addZip(3, "V3R2B6");
//        this.addZip(3, "V3R4Z6");
//        this.addZip(3, "V3M2B8");
//
//        //MCDONALDS
//        this.addZip(4, "V3V4F6");
//        this.addZip(4, "V3V9B7");
//        this.addZip(4, "V3V8S1");
//        this.addZip(4, "V3R2B3");
//        this.addZip(4, "V3M4Z0");
//        this.addZip(4, "V3M2B8");
//
//        //HAYACK SUSHI
//        this.addZip(5, "V3L0C3");
//        this.addZip(5, "V3V9B7");
//        this.addZip(5, "V3K8X1");
//        this.addZip(5, "V3L1AL");
//        this.addZip(5, "V3F4Z0");
//        this.addZip(5, "V3M2B8");
//
//    }

    public void PopulateDish(){
        ArrayList<Dish> dishes = new ArrayList<>();
        dishes.add(new Dish(1, 1, "Original Chicken Bucket (8 pcs) and Medium Fries",
                "Eight pieces of KFCs original recipe chicken served with a medium order of fries.",
                14.99));

        dishes.add(new Dish(2, 1, "Chicken Share Meal (6 pcs) and Medium Fries and Gravy",
                "Six pieces of KFCs original recipe chicken served with a medium order of fries and gravy.",
                16.00));

        dishes.add(new Dish(3, 1, "Chicken Share Meal (10 hot wings) and Medium Fries and Gravy",
                "Ten pieces of KFCs signature spicy, lightly-breaded wings served with a medium order of fries and gravy.",
                16.00));


        dishes.add(new Dish(1, 2, "Funghi Arrosto",
                "Baked mushrooms in a creamy mascarpone & spinach sauce with melted mozzarella and served with ciabatta toast.",
                20.00));

        dishes.add(new Dish(2, 2, "Mozzarella Carrozza",
                "Mini cheese bites in a golden herby breadcrumb, served with diced tomato, rocket & dolce piccante sweet chilli sauce.",
                10.49));

        dishes.add(new Dish(3, 2, "Tempura King Prawns",
                "King prawns in a light batter, served with garlic & lemon mayonnaise.",
                9.99));

        dishes.add(new Dish(1, 3, "6 inch Chicken Bacon Ranch Sandwich",
                "Juicy strips of grilled chicken and strips of bacon, all topped with tangy smooth ranch sauce.(530 cals)",
                7.49));

        dishes.add(new Dish(2, 3, "6 inch Steak and Cheese Sandwich",
                "Slices of hot tender, juicy steak, served on the bread of your choice, just bursting with flavour.(370 cals)",
                7.29));

        dishes.add(new Dish(3, 3, "Sweet Onion Chicken Teriyaki Sandwich",
                "Teriyaki-glazed chicken strips with the tangy taste of fresh veggies and succulent sweet onion! COntains 45 grams of fat(370 cals)",
                6.99));

        dishes.add(new Dish(1, 4, "Big Mac Meal [680-1120 Cals]",
                "Comes with medium fries or side salad and medium fountain drink or coffee.",
                9.29));

        dishes.add(new Dish(2, 4, "Mighty Angus Original Meal [950-1390 Cals]",
                "Comes with medium fries or side salad and medium fountain drink or coffee.",
                10.69));


        dishes.add(new Dish(3, 4, "Quarter Pounder with Cheese Meal [680-1120 Cals]",
                "Comes with medium fries or side salad and medium fountain drink or coffee.",
                9.29));

        dishes.add(new Dish(1, 5, "GESO KARAAGE",
                "Japanese style deep fried squid tentacles",
                4.95));

        dishes.add(new Dish(2, 5, "SALMON NIGIRI",
                "Wasabi inside",
                1.75));


        dishes.add(new Dish(3, 5, "B.C. ROLL",
                "Deep fried salmon skin and kappa",
                3.75));

        for(Dish d : dishes){
            System.out.println(d.getTitle() +": "+ this.addDish(d));
        }
    }

    public boolean isEmptyTable(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT "+RId+" from " +
                Restaurant ;
        Cursor c = sqLiteDatabase.rawQuery(query,null);
        if (c.getCount() < 1){
            return true;
        }
        return false;
    }

    //endregion
}
