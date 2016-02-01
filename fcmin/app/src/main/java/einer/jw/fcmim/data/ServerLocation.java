package einer.jw.fcmim.data;

/**
 * Defined the server location where the images are going to be send
 */
public class ServerLocation {
    private int id;
    private String url;
    private boolean active;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return this.url;
    }
}
