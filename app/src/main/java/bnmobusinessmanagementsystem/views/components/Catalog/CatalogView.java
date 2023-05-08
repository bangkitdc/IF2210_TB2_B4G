package bnmobusinessmanagementsystem.views.components.Catalog;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.TilePane;

public class CatalogView extends TilePane {
    public CatalogView(){
        this.setAlignment(Pos.TOP_LEFT);
        this.setPadding(new Insets(0,0,0,10));
        this.setHgap(20);
        this.setVgap(20);
        this.setStyle("-fx-background-color: null;");
        this.setMinHeight(634);
        this.setPadding(new Insets(0,0,40,10));
    }

    public void addItem(ItemView items){
        this.getChildren().add(items);
    }

    public void removeItem(int idx){
        this.getChildren().remove(idx);
    }

    public void clearCatalog(){
        this.getChildren().clear();
    }

    public void searchCatalog(String name){
//        for(Node item : this.getChildren()){
//            if(item instanceof Items){
//                System.out.println("SETBERHASIL");
//                System.out.println(((Items) item).getName().getText());
//                System.out.println(name);
//                if(((Items) item).getName().getText().equals(name)){
//                    this.getChildren().add(item);
//                    System.out.println("BERHASIL");
//                }
//            }
//        }
    }
}
