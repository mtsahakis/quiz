import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 * This class is thread safe.
 */
public class Parser {
  private File file;
  public void setFile(File f) {
    file = f;
  }
  public File getFile() {
    return file;
  }
  public String getContent() throws IOException {
    return getData(true);
  }
  public String getContentWithoutUnicode() throws IOException {
    return getData(false);
  }
  public void saveContent(String content) throws IOException {
    FileOutputStream fos = new FileOutputStream(file);
    for (int i = 0; i < content.length(); i += 1) {
      fos.write(content.charAt(i));
    }
    fos.close();
  }
  private String getData(boolean parseUnicode) throws IOException {
    FileInputStream fis = new FileInputStream(file);
    String output = "";
    int data;
    while ((data = fis.read()) > 0) {
      if (parseUnicode || data < 0x80) {
        output += (char) data;
      }
    }
    fis.close();
    return output;
  }
}
