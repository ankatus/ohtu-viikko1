package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenTilavuusNollautuu() {
        varasto = new Varasto(-1);
        assertEquals(0,varasto.getTilavuus(),vertailuTarkkuus);
    }

    @Test
    public void negatiivinenTilavuusNollautuuKuormitetussaKonstruktorissa() {
        varasto = new Varasto(-1,0);
        assertEquals(0,varasto.getTilavuus(),vertailuTarkkuus);
    }

    @Test
    public void negatiivinenAlkusaldoNollautuu() {
        varasto = new Varasto(10,-1);
        assertEquals(0,varasto.getSaldo(),vertailuTarkkuus);
    }

    @Test
    public void alkusaldoSuurempiKuinTilavuus() {
        varasto = new Varasto(10,11);
        assertEquals(10,varasto.getSaldo(),vertailuTarkkuus);
    }

    @Test
    public void alkusaldoAsettuuOikein() {
        varasto = new Varasto(10,5);
        assertEquals(5,varasto.getSaldo(),vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void lisaysEiYlitaTilavuutta() {
        varasto.lisaaVarastoon(11);
        assertEquals(10,varasto.getSaldo(),vertailuTarkkuus);
    }

    @Test
    public void negatiivinenLisaysEiKasvataSaldoa() {
        varasto.lisaaVarastoon(-1);
        assertEquals(0,varasto.getSaldo(),vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenEiTeeSaldostaNegatiivista() {
        varasto.lisaaVarastoon(5);
        varasto.otaVarastosta(6);
        assertEquals(0,varasto.getSaldo(),vertailuTarkkuus);
    }

    @Test
    public void negatiivinenOttaminenPalauttaaNolla() {
        double tulos = varasto.otaVarastosta(-5);
        assertEquals(0,varasto.getSaldo(),vertailuTarkkuus);
    }

    @Test
    public void toStringToimiiOikein() {
        varasto.lisaaVarastoon(5);
        assertEquals("saldo = 5.0, vielä tilaa 5.0", varasto.toString());
    }

}