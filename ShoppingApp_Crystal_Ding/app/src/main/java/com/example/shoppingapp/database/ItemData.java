package com.example.shoppingapp.database;

import com.example.shoppingapp.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemData {
    public static List<Item> dataItemList;
    public static Map<String, Item> dataItemMap;

    static {
        dataItemList = new ArrayList<>();
        dataItemMap = new HashMap<>();

        addItem(new Item("574a4ef4-705b-482d-9722-3384e498a5f0", "Campbell's Condensed Vegetable", 1.97, "abc.png",
                "Campbell’s Vegetable soup makes a satisfying and delicious lunchtime pairing with a sandwich or salad. It also provides 1 full serving of vegetables per 250 mL bowl without any artificial colours or flavours."));

        addItem(new Item("0c9c11f0-b4e3-4611-8595-357530625479", "Campbell's Condensed Chicken Noodle Soup", 1.97, "chicken_noodle.jpg",
                "Campbell’s Chicken Noodle soup has always been a Canadian classic. This soup starts with fresh noodles in every can. Prepared with fresh noodles and tender pieces of lean chicken simmered in a savoury chicken broth for the comforting soup you love. Plus, it contains no artificial colours or flavours and has 70 calories per 250 mL bowl."));

        addItem(new Item("e8266282-b0fd-439a-a291-a4eb46ba5606", "Maple Leaf Smoked Black Forest Ham", 5, "ham.jpg",
                "Choose Natural Selections™ smoked black forest ham when packing your lunch and you’ll skip the preservatives* and artificial ingredients. Look for Natural Selections™ Black Forest Ham where sliced meats are sold in your favourite grocery store. Conveniently sliced, and ready to go!"));
    }

    private static void addItem(Item item) {
        dataItemList.add(item);
        dataItemMap.put(item.getItemId(), item);
    }
}
