package CuongNVT.Book;

import java.io.Serializable;

public class BookDTO implements Serializable {

    private String ID;
    private String Name;
    private String Author;
    private String Description;
    private String ImgUrl;
    private int Price;

    public BookDTO(String ID, String Name, String Author, String Description, String ImgUrl, int Price) {
        this.ID = ID;
        this.Name = Name;
        this.Author = Author;
        this.Description = Description;
        this.ImgUrl = ImgUrl;
        this.Price = Price;
    }

    public BookDTO() {
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String Author) {
        this.Author = Author;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getImgUrl() {
        return ImgUrl;
    }

    public void setImgUrl(String ImgUrl) {
        this.ImgUrl = ImgUrl;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int Price) {
        this.Price = Price;
    }

}
