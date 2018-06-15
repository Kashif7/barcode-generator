import com.itextpdf.text.DocumentException;
import com.onbarcode.barcode.Code128;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;



public class Main {

    public static void main(String[] args) throws Exception {
        Code128 barcode = new Code128();

        final String uuid = UUID.randomUUID().toString().replace("-", "");

        barcode.setData(uuid);

// Set barcode data text to encode
        barcode.setX(2);

//// Generate barcode & encode into GIF format
//        barcode.drawBarcode("C://barcode-code128.gif");
//
//// Generate barcode & encode into JPEG format
        barcode.drawBarcode(uuid+".jpg");

//// Generate barcode & encode into EPS format
//        barcode.drawBarcode2EPS("C://barcode-code128.eps");
//
//// Generate barcode & print into Graphics2D object
//        barcode.drawBarcode("Java Graphics2D object");

        convert(uuid);

    }

    static void convert(String name) throws FileNotFoundException, DocumentException, IOException {
        File root = new File("");

        File yourFile = new File(name+".pdf");
        yourFile.createNewFile();

        String outputFile = name+".pdf";

        List<String> files = new ArrayList<String>();
        files.add(name+".jpg");

        Document document = new Document();

        PdfWriter.getInstance(document, new FileOutputStream(yourFile, false));
        document.open();

        for (String f : files) {
            document.newPage();
            Image image = Image.getInstance(new File(name+".jpg").getAbsolutePath());
            image.setAbsolutePosition(0, 0);
            image.setBorderWidth(0);
            image.scaleAbsoluteHeight(PageSize.A4.getHeight());
            image.scaleAbsoluteWidth(PageSize.A4.getWidth());
            document.add(image);
        }

        document.close();

    }
}
