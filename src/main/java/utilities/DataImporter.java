package fr.enssat.caronnantel.utilities;

import fr.enssat.caronnantel.model.Flower;
import fr.enssat.caronnantel.model.IrisClass;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class DataImporter {

    private Map<Flower,IrisClass> importData(String fileName) throws IOException {
        File csvData = new File(getClass().getClassLoader().getResource(fileName).getFile()); // This is a CSV file. We assume that it is existing.
        CSVParser parser = CSVParser.parse(csvData, Charset.defaultCharset(), CSVFormat.RFC4180);

        Map<Flower,IrisClass> data = new HashMap<>();
        for (CSVRecord csvRecord : parser) {
            Flower flower = new Flower();
            // Warning: the order matter ! See the CSV file description
            flower.setSepalLength(Double.parseDouble(csvRecord.get(0)));
            flower.setSepalWidth(Double.parseDouble(csvRecord.get(1)));
            flower.setPetalLength(Double.parseDouble(csvRecord.get(2)));
            flower.setPetalWidth(Double.parseDouble(csvRecord.get(3)));

            data.put(flower, IrisClass.forValue(csvRecord.get(4)));
        }
        return data;
    }

    public Map<Flower, IrisClass> getTrainingSet() throws IOException {
        return importData("training.data");
    }

    public Map<Flower, IrisClass> getTestSet() throws IOException {
        return importData("test.data");
    }
}
