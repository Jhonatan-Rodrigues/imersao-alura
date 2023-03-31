import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
        System.out.println(listaDeFilmes.get(0));


        var diretorio = new File("figurinhas/");
        diretorio.mkdir();

        GeradoraDeFigurinhas geradora = new GeradoraDeFigurinhas();
        for (Map<String,String> filme : listaDeFilmes) {

            String urlImagem = filme.get("image");
            String titulo = filme.get("title");


            InputStream inputStream = new URL(urlImagem).openStream();
            String nomeArquivo = "figurinhas/"+ titulo + ".png";


            System.out.println(titulo);
            double numeroEstrelinhas = Double.parseDouble(filme.get("imDbRating"));
            int classificacao = (int) numeroEstrelinhas;

            String textoFigurinha;
            if (numeroEstrelinhas >= 8) {
                textoFigurinha = "TOPZERA";
            }else{
                textoFigurinha = "LÁ NELE";
            }
            geradora.cria(inputStream, nomeArquivo, textoFigurinha);

            for(int i = 0; i<=classificacao;i++) {
                System.out.print("⭐");
            }
            System.out.println();


        }
    }
}
