package app.data;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class DataService {

    private List<String> imgLinks;

    public DataService(List<String> imgLinks)
    {
        this.imgLinks = imgLinks;
    }

    public List<String> getImgLinks() {
        return imgLinks;
    }

    public void setImgLinks(List<String> imgLinks) {
        this.imgLinks = imgLinks;
    }

    public void saveImages()
    {
        URL url;
        BufferedImage img;
        File file;

        for (String href : imgLinks)
        {
            try {
                url = new URL(href);
                img = ImageIO.read(url);

                file = new File("F:\\" + href.substring(href.lastIndexOf('/') + 1));
                ImageIO.write(img, href.substring(href.lastIndexOf('.') + 1), file);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
