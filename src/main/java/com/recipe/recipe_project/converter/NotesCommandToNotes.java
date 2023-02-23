package com.recipe.recipe_project.converter;

import com.recipe.recipe_project.command.NotesCommand;
import com.recipe.recipe_project.domain.Notes;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class NotesCommandToNotes implements Converter<NotesCommand, Notes> {
    @Synchronized
    @Nullable
    @Override
    public Notes convert(NotesCommand notesCommand) {
        if (notesCommand==null)
            return null;

        final Notes notes = new Notes();
        notes.setNotes(notesCommand.getNotes());
        notes.setId(notesCommand.getId());
        return notes;
    }
}
