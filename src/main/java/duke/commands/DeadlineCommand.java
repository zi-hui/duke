package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.exceptions.DeadlineTaskException;
import duke.exceptions.DeadlineDateException;

public class DeadlineCommand extends ExecuteCommand {
    public DeadlineCommand(String userData) {
        this.userData = userData;
        this.toExit = false;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) throws DeadlineTaskException, DeadlineDateException {
        if (!userData.trim().toLowerCase().equals("deadline")) {
            int indexOfBy = userData.indexOf("/by");
            if (indexOfBy == -1) {
                throw new DeadlineDateException("Please specify a date by using \'/by\'!");
            }
            String[] newData = userData.split(" /by ");
            if (newData.length == 1) {
                throw new DeadlineDateException("Please specify a date after \'/by\'!");
            }
            tasks.deadlineTask(newData[0], newData[1]);
            storage.saveData(tasks);
        } else {
            throw new DeadlineTaskException("Add a task behind 'deadline', task cannot be left empty.");
        }
    }
}