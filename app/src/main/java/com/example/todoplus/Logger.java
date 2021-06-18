package com.example.todoplus;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.prefs.Preferences;

public class Logger {

    SharedPreferences sharedPreferences;

    public Logger(Context context) {
        sharedPreferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
    }

    public void save(List<Note> notes) {

        JSONArray jsonNotes = new JSONArray();
        for (Note note : notes) {
            JSONObject jsonNote = new JSONObject();
            try {
                jsonNote.put("title", note.title);
                jsonNote.put("content",note.content);
                jsonNotes.put(jsonNote);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        sharedPreferences.edit().putString("notes", jsonNotes.toString()).commit();
    }

    public List<Note> restore(){
        String data = sharedPreferences.getString("notes", "");
        List<Note> notes = new ArrayList<Note>();
        try {
            JSONArray jsonNotes = new JSONArray(data);
            for (int i = 0; i < jsonNotes.length(); i++)
            {
                JSONObject jsonNote = jsonNotes.getJSONObject(i);
                String title = jsonNote.getString("title");
                String content = jsonNote.getString("content");
                notes.add(new Note(title, content));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return notes;
    }

}
