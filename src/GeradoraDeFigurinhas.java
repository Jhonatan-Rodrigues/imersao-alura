import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class GeradoraDeFigurinhas {
    public void cria(InputStream inputStream, String nomeArquivo, String texto) throws Exception {

        // Leitura da imagem
        //FileInputStream fileInputStream = new FileInputStream(new File("entrada/filmes.jpg"));
       // InputStream inputStream = new URL("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularTVs_2.jpg").openStream();
        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        //cria nova imagem em memória com trasparencia e novo tamanho
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;

        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        //copiar imagem para nova imagem(em memoria)
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);



        //configurar a fonte
        var fonte = new Font("Impact", Font.BOLD, 80);
        graphics.setColor(Color.YELLOW);
        graphics.setFont(fonte);


        //escrever uma nova frase na imagem
        FontMetrics fontMetrics = graphics.getFontMetrics();
        Rectangle2D retangulo = fontMetrics.getStringBounds(texto, graphics);
        int larguraTexto = (int) retangulo.getWidth();
        int posicaoTextoX  = (largura - larguraTexto) / 2;
        graphics.drawString(texto, posicaoTextoX, novaAltura - 100);
        //escrever a nova imagem em um arquivo
        ImageIO.write(novaImagem, "png", new File(nomeArquivo));
    }


}
