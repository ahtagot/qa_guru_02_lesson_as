package workwithzip.model;

public class LibraryBookPojo {
    private String title;
    private String[] volume;
    private int totalPages;


    private Storage storage;

    public LibraryBookPojo() {
    }

    public LibraryBookPojo(String title, String[] volume, int totalPages, Storage storage) {
        this.title = title;
        this.volume = volume;
        this.totalPages = totalPages;
        this.storage = storage;
    }


    public String getTitle() {
        return title;
    }

    public String[] getVolume() {
        return volume;
    }

    public int getTotalPages() {
        return totalPages;
    }


    public Storage getStorage() {
        return storage;
    }

}
