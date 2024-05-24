// Producto
class Documento {
    private String contenido;
    private String formato;

    public Documento(String contenido, String formato) {
        this.contenido = contenido;
        this.formato = formato;
    }

    public void mostrar() {
        System.out.println("Contenido: " + contenido);
        System.out.println("Formato: " + formato);
    }
}

// Builder
interface DocumentoBuilder {
    void buildTexto(String contenido);
    void buildHTML(String contenido);
    Documento getDocumento();
}

// ConcreteBuilder
class DocumentoBuilderTexto implements DocumentoBuilder {
    private Documento documento;

    public DocumentoBuilderTexto() {
        documento = new Documento("", ".txt");
    }

    @Override
    public void buildTexto(String contenido) {
        documento = new Documento(contenido, ".txt");
    }

    @Override
    public void buildHTML(String contenido) {
        // No se implementa en este builder
    }

    @Override
    public Documento getDocumento() {
        return documento;
    }
}

// ConcreteBuilder
class DocumentoBuilderHTML implements DocumentoBuilder {
    private Documento documento;

    public DocumentoBuilderHTML() {
        documento = new Documento("", ".html");
    }

    @Override
    public void buildTexto(String contenido) {
        // No se implementa en este builder
    }

    @Override
    public void buildHTML(String contenido) {
        documento = new Documento(contenido, ".html");
    }

    @Override
    public Documento getDocumento() {
        return documento;
    }
}

// Director
class Director {
    public Documento construirDocumentoTexto(DocumentoBuilder builder, String contenido) {
        builder.buildTexto(contenido);
        return builder.getDocumento();
    }

    public Documento construirDocumentoHTML(DocumentoBuilder builder, String contenido) {
        builder.buildHTML(contenido);
        return builder.getDocumento();
    }
}

// Clase principal
public class Main {
    public static void main(String[] args) {
        Director director = new Director();

        DocumentoBuilderTexto builderTexto = new DocumentoBuilderTexto();
        Documento documentoTexto = director.construirDocumentoTexto(builderTexto, "Contenido en formato texto");
        documentoTexto.mostrar();

        System.out.println();

        DocumentoBuilderHTML builderHTML = new DocumentoBuilderHTML();
        Documento documentoHTML = director.construirDocumentoHTML(builderHTML, "<html><body><h1>Título</h1></body></html>");
        documentoHTML.mostrar();
    }
}