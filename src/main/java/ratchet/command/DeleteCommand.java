package ratchet.command;

import ratchet.exception.InvalidCommandArgumentException;
import ratchet.storage.Storage;
import ratchet.task.Task;
import ratchet.task.TaskList;
import ratchet.ui.Ui;

/**
 * Command to delete task from task list.
 */
public class DeleteCommand extends Command {
    private final Integer[] indexes;

    /**
     * Constructor for DeleteCommand.
     *
     * @param indexes Index of task to be deleted.
     */
    public DeleteCommand(Integer[] indexes) {
        this.indexes = indexes;
    }

    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) throws InvalidCommandArgumentException {
        StringBuilder res = new StringBuilder(ui.printWithSeparator("Noted. I've removed these task:"));
        try {
            for (int index : indexes) {
                Task task = tasks.deleteTask(index);
                res.append(ui.printWithSeparator(task.toString()));
            }
            return res.toString() + ui.printWithSeparator(tasks.toString());
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandArgumentException("Please enter a valid task index!");
        }
    }
}
