import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;

public class PDFWriter {

    public static void saveToPDF(Statistics stats) throws IOException {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        String text = "You added " + stats.getNumOfGivenWords() + " words from this languages: " + stats.getLanguageNames();

        PDFont font = PDType1Font.HELVETICA_BOLD;
        int fontSize = 16;
        int marginTop = 30;
        float titleWidth = font.getStringWidth(text) / 1000 * fontSize;
        float titleHeight = font.getFontDescriptor().getFontBoundingBox().getHeight() / 1000 * fontSize;


        contentStream.setFont(font, fontSize);
        contentStream.beginText();
        contentStream.newLineAtOffset((page.getMediaBox().getWidth() - titleWidth) / 2,
                page.getMediaBox().getHeight() - marginTop - titleHeight);
        contentStream.showText(text);
        contentStream.endText();
        contentStream.close();

        document.save("C:\\Users\\jedrz\\IdeaProjects\\iteration\\src\\main\\java\\stats.pdf");
        document.close();
    }
}

