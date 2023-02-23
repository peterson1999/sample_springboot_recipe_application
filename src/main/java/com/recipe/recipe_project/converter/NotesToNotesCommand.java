package com.recipe.recipe_project.converter;

import com.recipe.recipe_project.command.CategoryCommand;
import com.recipe.recipe_project.command.NotesCommand;
import com.recipe.recipe_project.domain.Category;
import com.recipe.recipe_project.domain.Notes;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class NotesToNotesCommand implements Converter<Notes, NotesCommand> {
    @Synchronized
    @Nullable
    @Override
    public NotesCommand convert(Notes notes) {
        if (notes==null)
            return null;

        final NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(notes.getId());
        notesCommand.setNotes(notes.getNotes());
        return notesCommand;
    }
}
