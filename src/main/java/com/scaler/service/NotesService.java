package com.scaler.service;

import com.scaler.entities.NoteEntity;
import com.scaler.entities.TaskEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class NotesService {

    private TaskService taskService;
    private HashMap<Integer,TaskNotesHolder> taskNotesHolder = new HashMap<>();

    public NotesService(TaskService taskService){
        this.taskService = taskService;
    }

    class TaskNotesHolder{
        protected int noteId = 1;
        protected ArrayList<NoteEntity> notes = new ArrayList<>();
    }

    public List<NoteEntity> getNotesForTask(int taskId){
        TaskEntity task = taskService.getTaskById(taskId);
        if(task == null){
            return null;
        }

        // or we can directly write "return taskNotesHolder.getOrDefault(taskId, new TaskNotesHolder()).notes;"

        if(taskNotesHolder.get(taskId) == null){
            return taskNotesHolder.put(taskId, new TaskNotesHolder()).notes;
        }
        return taskNotesHolder.get(taskId).notes;

    }

    public NoteEntity addNoteForTask(int taskId, String title, String body){
        TaskEntity task = taskService.getTaskById(taskId);
        if(task == null){
            return null;
        }

        if(taskNotesHolder.get(taskId)==null){
            taskNotesHolder.put(taskId, new TaskNotesHolder());
        }

        TaskNotesHolder taskNotesHolder1 = taskNotesHolder.get(taskId);
        NoteEntity noteEntity = new NoteEntity();
        noteEntity.setId(taskNotesHolder1.noteId);
        noteEntity.setTitle(title);
        noteEntity.setBody(body);
        taskNotesHolder1.notes.add(noteEntity);
        taskNotesHolder1.noteId++;
        return noteEntity;
    }


}
