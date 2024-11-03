package com.example.parcial.services;

import com.example.parcial.entities.Persona;
import com.example.parcial.repositories.BaseRepository;
import com.example.parcial.repositories.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Service
public class PersonaServiceImpl extends BaseServiceImpl<Persona, Long> implements PersonaService, Serializable {

    @Autowired
    protected PersonaRepository repository;

    public PersonaServiceImpl(BaseRepository<Persona, Long> baseRepository) {
        super(baseRepository);
    }


    //convierto la lista de string en una matriz
    public static char[][] convertToMatrix(List<String> dna) {
        int rows = dna.size();
        int cols = dna.get(0).length();
        char[][] matrix = new char[rows][cols];

        for (int i = 0; i < rows; i++) {
            String str = dna.get(i);
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = str.charAt(j);
            }
        }
        return matrix;
    }

    //hago la comparacion de los primeros 4 elementos de la lusta
    public static boolean areEqual(List<Character> fourLetters){
        if(fourLetters.get(0) == fourLetters.get(1) &&
                fourLetters.get(1) == fourLetters.get(2) &&
                fourLetters.get(2) == fourLetters.get(3)){
            return true;
        }
        else{
            return false;
        }
    }

    //los que son distintos los guardo
    private static final HashSet<Character> distintosEncontrados = new HashSet<>();
    private static final List<Character> fourLetters = new ArrayList<>();

    public static void analizePatron(char first, char second, char third, char forth) {
        boolean found = false;

        fourLetters.add(first);
        fourLetters.add(second);
        fourLetters.add(third);
        fourLetters.add(forth);
        System.out.println("fourLetters: " + fourLetters.toString());

        //se fija si los 4 son iguales
        found = areEqual(fourLetters);
        fourLetters.clear();
        if (found) {
            distintosEncontrados.add(first);
        }
    }

    public static boolean isMutant(List<String> dna){

        char[][] newDna = convertToMatrix(dna);
        //Para ver los Errores
        if(dna.isEmpty()){throw new RuntimeException("Error: Lista vacía");}
        if(dna.get(0).length() != dna.size()){throw new RuntimeException("Error: La matriz debe ser cuadrada");}

        int N = newDna.length-1;

        //Iterar sobre la matriz
        for(int i = 0; i <= N; i++){
            for(int j = 0; j <= N; j+=2){
                if(j <= N-3) {
                    //ir por la linea horizontal
                    if (newDna[i][j] == newDna[i][j + 2]) {
                        try {
                            analizePatron(newDna[i][j - 1], newDna[i][j], newDna[i][j + 1], newDna[i][j + 2]);
                        } catch (ArrayIndexOutOfBoundsException ignored) {}
                        finally {
                            try {
                                analizePatron(newDna[i][j], newDna[i][j+1], newDna[i][j+2], newDna[i][j+3]);
                            } catch (ArrayIndexOutOfBoundsException ignored) {}
                        }
                    }

                    //verificar por vertical
                    if (newDna[j][i] == newDna[j + 2][i]) {
                        try {
                            analizePatron(newDna[j-1][i], newDna[j][i], newDna[j+1][i], newDna[j+2][i]);
                        } catch (ArrayIndexOutOfBoundsException ignored) {}
                        finally {
                            try {

                                analizePatron(newDna[j][i], newDna[j+1][i], newDna[j+2][i], newDna[j+3][i]);
                            } catch (ArrayIndexOutOfBoundsException ignored) {}
                        }
                    }
                }

                //verificar por diagonales
                if(i <= N-3 && j <= N-3) { //diagonal izq derecha
                    if(newDna[i][j] == newDna[i+2][j+2]){
                        try {
                            analizePatron(newDna[i-1][j-1], newDna[i][j], newDna[i+1][j+1], newDna[i+2][j+2]);
                        } catch (ArrayIndexOutOfBoundsException ignored) {}
                        finally {
                            try {
                                analizePatron(newDna[i][j], newDna[i+1][j+1], newDna[i+2][j+2], newDna[i+3][j+3]);
                            } catch (ArrayIndexOutOfBoundsException ignored) {}
                        }
                    }
                }
                if(i <= N-3 && j >=3) { //diagonal derecha izq
                    if (newDna[i][j] == newDna[i + 2][j - 2]) {
                        try {
                            analizePatron(newDna[i-1][j+1], newDna[i][j], newDna[i+1][j-1], newDna[i+2][j-2]);

                        } catch (ArrayIndexOutOfBoundsException ignored) {}
                        finally {
                            try {
                                analizePatron(newDna[i][j], newDna[i+1][j-1], newDna[i+2][j-2], newDna[i+3][j-3]);
                            } catch (ArrayIndexOutOfBoundsException ignored) {}
                        }
                    }
                }

                //ver si ya existe más de un elemento distinto encontrado
                if(distintosEncontrados.size() > 1){
                    System.out.println("Elementos distintos encontrados: " + distintosEncontrados.size());
                    for(char elem : distintosEncontrados){
                        System.out.println(elem);
                    }
                    System.out.println("True");
                    distintosEncontrados.clear();
                    return true;
                }
            }
        }
        System.out.println("False");
        distintosEncontrados.clear();
        return false;
    }

    //calcular las estadísticas
    public HashMap<String,Serializable> getStats(){

        HashMap<String,Serializable> data = new HashMap<>();

        int countHumans = repository.findQuantityOfHumans();
        int countMutants = repository.findQuantityOfMutants();

        double ratio = (double)countMutants/(double)countHumans;

        data.put("cantMutantes", countMutants);
        data.put("cantHumanos", countHumans);
        data.put("ratio", ratio);

        return data;
    }
}
