package ingredients;


import io.qameta.allure.junit4.DisplayName;
import supertest.SuperTest;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class IngredientsTests extends SuperTest {

    @Before
    public void setUp() {
        doBefore();
        mainPage.openMainPage();
    }
    @Test
    @DisplayName("Проверка перехода к разделу \"Булки\"")
    public void bunTransitionTest(){
        mainPage.clickSauceTitle();
        mainPage.clickBunTitle();
        assertTrue(mainPage.isBunSelected());
    }
    @Test
    @DisplayName("Проверка перехода к разделу \"Соусы\"")
    public void sauceTransitionTest(){
        mainPage.clickSauceTitle();
        assertTrue(mainPage.isSauceSelected());
    }
    @Test
    @DisplayName("Проверка перехода к разделу \"Начинки\"")
    public void fillingsTransitionTest(){
        mainPage.clickFillingsTitle();
        assertTrue(mainPage.isFillingsSelected());
    }
}
