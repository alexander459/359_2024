package mainClasses.helpers;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateAdapter extends TypeAdapter<LocalDate> {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public void write(JsonWriter jsonWriter, LocalDate localDate) throws IOException {
        if(localDate == null)
            jsonWriter.nullValue();
        else
            jsonWriter.value(localDate.format(formatter));
    }

    @Override
    public LocalDate read(JsonReader jsonReader) throws IOException {
        String date = jsonReader.nextString();
        if(date.isEmpty())
            return null;
        return LocalDate.parse(date, formatter);
    }
}