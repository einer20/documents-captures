package einer.jw.fcmim.data.entities;

/**
 * Created by Einer on 2/2/2016.
 */
public class FileUploadRegisterModel {
    private String filePath;
    private int id;
    private boolean wasSend;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isWasSend() {
        return wasSend;
    }

    public void setWasSend(boolean wasSend) {
        this.wasSend = wasSend;
    }
}
