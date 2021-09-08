/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fes.aragon.inicio;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import java.text.DecimalFormat;
public class Inicio {
    public static void main(String[] args) throws IOException {
        double[][] cantidades = new double[20][13];
        double diferencia;
        try {
            FileInputStream archivo = new FileInputStream(new File("C:\\presenciaredes.xlsx"));
            XSSFWorkbook excel = new XSSFWorkbook(archivo);
            XSSFSheet hoja = excel.getSheetAt(0);
            int numFilas = hoja.getLastRowNum() + 1;
            for (int i = 0; i < numFilas; i++) {
                int x = 0;
                Row fila = hoja.getRow(i);
                int numCols = fila.getLastCellNum();
                for (int j = 0; j < numCols; j++) {
                    Cell celda = fila.getCell(j);
                    switch (celda.getCellTypeEnum().toString()) {
                        case "NUMERIC":
                            cantidades[i][x] = celda.getNumericCellValue();
                            /*System.out.print(celda.getNumericCellValue() + " ");*/
                            ++x;
                            break;
                    }
                }
                /*System.out.println(" ");*/
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        double valorT;
        double promedioFacebook;
        double promedioTwitter;
        String patron = "#.##";
        DecimalFormat decimal = new DecimalFormat(patron);
        diferencia = cantidades[8][6] - cantidades[8][1];
        System.out.println("Diferencia de followers en Twitter Enero a Junio " + diferencia);
        System.out.println("Calculo de promedio porcentaje de crecimiento Facebook y Twitter\t");
        promedioFacebook=((cantidades[3][1]+cantidades[3][2]+cantidades[3][3]+cantidades[3][4]+cantidades[3][5]+cantidades[3][6])*100)/6;
        promedioTwitter=((cantidades[10][1]+cantidades[10][2]+cantidades[10][3]+cantidades[10][4]+cantidades[10][5]+cantidades[10][6])*100)/6;
        String doubleTruncadoF = decimal.format(promedioFacebook);
        String doubleTruncadoT = decimal.format(promedioTwitter);
        System.out.println("Promedio de crecimiento Facebook durante 6 meses Enero-Junio "+doubleTruncadoF+"%");
        System.out.println("Promedio de crecimiento Twitter durante 6 meses Enero-Junio "+doubleTruncadoT+"%");
        System.out.println("Teclee los meses que desee comparar en visualizaciones en Youtube");
        System.out.println("1:Enero - 6:Junio");
        System.out.println("Primer Mes");
        Scanner input = new Scanner(System.in);
        int mes1 = input.nextInt();
        System.out.println("Segundo Mes");
        int mes2 = input.nextInt();
        if(mes2<mes1 || mes2>6){
            System.out.println("Porfavor confirme que el segundo mes no sea menor que el primero o mayor que 6");
            return; 
        }
        double valor1=cantidades[16][mes1];
        double valor2=cantidades[16][mes2];
        if(valor1<valor2){
           valorT=valor2-valor1; 
        }else{
           valorT=valor1-valor2;
        }
        System.out.println("La diferencia es de "+valorT+" visualizaciones");
        System.out.println("Fin del programa");
    }
}
