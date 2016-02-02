package einer.jw.fcmim.data.entities;

/**
 * Defined the server location where the images are going to be send
 */
public class ServerLocation {
    private int id;
    private String url;
    private boolean active;
    private int authentication_type;

    /*
    Indicates that the authentication will be perform in the three way authentication
    by making the followings travels:
    mobile-cloud->clientserver and then going backwards clientserver->cloud->mobile
     */
    public final int WEB_AUTHENTICATION = 1;

    /*
    Indicates that the authentication will be made in a local network
    over wifi.
     */
    public final int LOCAL_NETWORK_AUTHENTICATION = 2;

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

    public int getAuthentication_type() {
        return authentication_type;
    }

    public void setAuthentication_type(int authentication_type) {
        this.authentication_type = authentication_type;
    }
}
