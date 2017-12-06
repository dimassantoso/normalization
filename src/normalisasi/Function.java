/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package normalisasi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author dimassantoso
 */
public class Function {

    public Function() {

    }

    public HashMap<Integer, List<Double>> mean(HashMap<Integer, List<Double>> dataMap) {
        HashMap<Integer, List<Double>> dataMean = new HashMap<>();
        double mean;

        int count = 0;
        for (int i = 0; i < dataMap.get(i).size(); i++) {
            double sum = 0;
            List<Double> result = new ArrayList<>();
            for (int j = 0; j < dataMap.size(); j++) {
                sum += dataMap.get(j).get(i);  //Menjumlahkan satu kolom
            }
            mean = Math.round((sum / dataMap.size()) * 100d) / 100d; //Pembualatan
            result.add(mean); //Penampung list hasil mean
            dataMean.put(i, result); //Dimasukkan ke hash
        }
        return dataMean;

    }

    public HashMap<Integer, List<Double>> standarDev(HashMap<Integer, List<Double>> dataMap) {
        HashMap<Integer, List<Double>> dataStandarDev = new HashMap<>();
        double stdDev;

        for (int i = 0; i < dataMap.get(i).size(); i++) {
            double counting = 0;
            List<Double> result = new ArrayList<>();
            for (int j = 0; j < dataMap.size(); j++) {
                counting += Math.pow(dataMap.get(j).get(i) - mean(dataMap).get(i).get(0), 2);
            }
            stdDev = Math.sqrt(counting / (dataMap.size()-1));
            stdDev = Math.round(stdDev * 100d) / 100d; //Pembualatan
            result.add(stdDev);
            dataStandarDev.put(i, result); //penampung list standar deviasi
        }

        return dataStandarDev;
    }

    public HashMap<Integer, List<Double>> MaxValue(HashMap<Integer, List<Double>> dataMap) {
        HashMap<Integer, List<Double>> maxValue = new HashMap<>();

        for (int i = 0; i < dataMap.get(i).size(); i++) {
            double max = 0; //Inisialisasi 0
            List<Double> temp = new ArrayList<>();
            for (int j = 0; j < dataMap.size(); j++) {
                if (max < dataMap.get(j).get(i)) {
                    max = dataMap.get(j).get(i);
                }
            }
            max = Math.round(max * 100d) / 100d; //Pembualatan
            temp.add(max);
            maxValue.put(i, temp);
        }
        return maxValue;
    }

    public HashMap<Integer, List<Double>> MinValue(HashMap<Integer, List<Double>> dataMap) {
        HashMap<Integer, List<Double>> minValue = new HashMap<>();

        for (int i = 0; i < dataMap.get(i).size(); i++) {
            double min = Double.MAX_VALUE; //Inisialisasi nilai tinggi
            List<Double> temp = new ArrayList<>();
            for (int j = 0; j < dataMap.size(); j++) {
                if (min > dataMap.get(j).get(i)) {
                    min = dataMap.get(j).get(i);
                }
            }
            min = Math.round(min * 100d) / 100d; //Pembualatan
            temp.add(min);
            minValue.put(i, temp);
        }
        return minValue;
    }

    public HashMap<Integer, List<Double>> MinMax(HashMap<Integer, List<Double>> dataMap) {
        HashMap<Integer, List<Double>> MinMaxResult = new HashMap<>();
        double min = 0;
        double max = 1;

        for (int i = 0; i < dataMap.size(); i++) {
            //double newData = 0;
            List<Double> temp = new ArrayList<>();
            for (int j = 0; j < dataMap.get(j).size(); j++) {
                double newData = ((dataMap.get(i).get(j) - MinValue(dataMap).get(j).get(0)) * (max - min))
                        / ((MaxValue(dataMap).get(j).get(0) - MinValue(dataMap).get(j).get(0)) + min);
                newData = Math.round(newData * 100d) / 100d; 
                temp.add(newData);
            }
            MinMaxResult.put(i, temp);
        }
        return MinMaxResult;
    }

    public HashMap<Integer, List<Double>> zScore(HashMap<Integer, List<Double>> dataMap) {
        HashMap<Integer, List<Double>> zScoreResult = new HashMap<>();
        for (int i = 0; i < dataMap.size(); i++) {
            List<Double> temp = new ArrayList<>();
            for (int j = 0; j < dataMap.get(j).size(); j++) {
                double newData = (dataMap.get(i).get(j) - mean(dataMap).get(j).get(0)) / standarDev(dataMap).get(j).get(0);
                newData = Math.round(newData * 100d) / 100d;
                temp.add(newData);
            }
            zScoreResult.put(i, temp);
        }
        return zScoreResult;

    }

    public HashMap<Integer, List<Double>> decimalScalling(HashMap<Integer, List<Double>> dataMap) {
        HashMap<Integer, List<Double>> decimalResult = new HashMap<>();

        for (int i = 0; i < dataMap.size(); i++) {
            List<Double> temp = new ArrayList<>();
            for (int j = 0; j < dataMap.get(j).size(); j++) {
                double newData = dataMap.get(i).get(j) / Math.pow(10, 2);
                newData = Math.round(newData * 100d) / 100d;
                temp.add(newData);
            }
            decimalResult.put(i, temp);
        }
        return decimalResult;
    }

    public HashMap<Integer, List<Double>> sigmoidal(HashMap<Integer, List<Double>> dataMap) {
        HashMap<Integer, List<Double>> sigmoidalResult = new HashMap<>();
        for (int i = 0; i < dataMap.size(); i++) {
            List<Double> temp = new ArrayList<>();
            for (int j = 0; j < dataMap.get(j).size(); j++) {
                double newData = (1 - Math.exp(-((dataMap.get(i).get(j) - mean(dataMap).get(j).get(0))/standarDev(dataMap).get(j).get(0))))
                        / (1 + Math.exp(-((dataMap.get(i).get(j) - mean(dataMap).get(j).get(0))/standarDev(dataMap).get(j).get(0))));
                newData = Math.round(newData * 100d) / 100d;
                temp.add(newData);
            }
            sigmoidalResult.put(i, temp);
        }
        return sigmoidalResult;
    }

    public HashMap<Integer, List<Double>> softmax(HashMap<Integer, List<Double>> dataMap) {
        HashMap<Integer, List<Double>> softmaxResult = new HashMap<>();
        int x = 10; //Respon linear
        for (int i = 0; i < dataMap.size(); i++) {
            List<Double> temp = new ArrayList<>();
            for (int j = 0; j < dataMap.get(j).size(); j++) {
                double newData = 1 / (1 + Math.exp(-((dataMap.get(i).get(j) - mean(dataMap).get(j).get(0))
                        / (x * (standarDev(dataMap).get(j).get(0) / (2*3.14))))));
                newData = Math.round(newData * 100d) / 100d;
                temp.add(newData);
            }
            softmaxResult.put(i, temp);
        }
        return softmaxResult;
    }

}
