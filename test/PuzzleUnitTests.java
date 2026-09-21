import java.io.StringWriter;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PuzzleUnitTests {
    @Test
    public void testConstructor() {
        Board b = new Board("023145678");
        StringWriter writer = new StringWriter();
        PrintWriter pw = new PrintWriter(writer);
        pw.println(" 23");
        pw.println("145");
        pw.println("678");
        // JUnit expects the expected value first, then the actual value.
        assertEquals(writer.toString(), b.toString());
        pw.close();
    }

    @Test
    public void testConstructor2() {
        Board b = new Board("123485670");
        StringWriter writer = new StringWriter();
        PrintWriter pw = new PrintWriter(writer);
        pw.println("123");
        pw.println("485");
        pw.println("67 ");
        assertEquals(writer.toString(), b.toString());
        pw.close();
    }

    @Test
    public void equals(){
        Board b = new Board("876543210");
        Object o = new Board("876543210");
        assertEquals(b, o);
    }

    @Test
    public void diferentes(){
        Board b = new Board("678543210");
        Object o = new Board("876543210");
        assertNotEquals(b, o);
    }

    @Test
    public void identidade(){
        Board b = new Board("876543210");
        assertEquals(b, b);
    }

    @Test
    public void nulo(){
        Board b = new Board("678543210");
        assertNotEquals(null, b);
    }

    @Test
    public void Clone(){
        Board a = new Board("023145678");
        Board b = a.clone();
        assertEquals(a,b);
    }

    @Test
    public void codigo_hash(){
        Board a = new Board("023145678");
        Board b = a.clone();
        assertEquals(a.hashCode(),b.hashCode());
    }

    @Test
    public void Teste_children_2_movimentos(){
        Board b = new Board("012345678");
        List<Ilayout> filhos = b.children();
        assertEquals(2,filhos.size());

        Board c = new Board("102345678");
        Board d = new Board("312045678");
        assertTrue(filhos.contains(c));
        assertTrue(filhos.contains(d));
    }

    @Test
    public void Teste_children_3_movimentos(){
        Board b = new Board("102345678");
        List<Ilayout> filhos = b.children();
        assertEquals(3,filhos.size());
    }

    @Test
    public void Teste_children_4_movimentos(){
        Board b = new Board("123405678");
        List<Ilayout> filhos = b.children();
        assertEquals(4,filhos.size());
    }

    @Test
    public void test_solve_1(){
        int contador = 0;
        Board inicial = new Board("123456078");
        Board objectivo = new Board("123456780");

        BestFirst best = new BestFirst();
        Iterator<BestFirst.State> res = best.solve(inicial,objectivo);

        assertNotNull(res);
        while(res.hasNext()){
            contador++;
            res.next();
        }
        assertEquals(3,contador);
    }

    @Test
    public void test_solve_2(){
        int contador = 0;
        Board inicial = new Board("123456780");
        Board objectivo = new Board("123456780");

        BestFirst best = new BestFirst();
        Iterator<BestFirst.State> res = best.solve(inicial,objectivo);

        assertNotNull(res);
        while(res.hasNext()){
            contador++;
            res.next();
        }
        assertEquals(1,contador);
    }

    @Test
    public void test_solve_3(){
        Board inicial = new Board("123456870");
        Board objectivo = new Board("123456780");

        BestFirst best = new BestFirst();
        Iterator<BestFirst.State> res = best.solve(inicial,objectivo);

        assertNull(res);
    }

    // TODO: Add tests for the remaining Board methods and for solve.
}
