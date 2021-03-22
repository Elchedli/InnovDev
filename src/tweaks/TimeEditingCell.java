/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tweaks;

/**
 *
 * @author shidono
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.jfoenix.controls.JFXTimePicker;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import models.Suivi;

/**
 *
 * @author shidono
 */
public class TimeEditingCell extends TableCell<Suivi, Time> {

        private JFXTimePicker TimePicker;
        public TimeEditingCell() {
        }

        @Override
        public void startEdit() {
            if (!isEmpty()) {
                super.startEdit();
                createDatePicker();
                setText(null);
                setGraphic(TimePicker);
            }
        }

        @Override
        public void cancelEdit() {
            super.cancelEdit();

            setText(getTime().toString());
            setGraphic(null);
        }

        @Override
        public void updateItem(Time item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                if (isEditing()) {
                    if (TimePicker != null) {
                        TimePicker.setValue(getTime());
                    }
                    setText(null);
                    setGraphic(TimePicker);
                } else {
                    setText(getTime().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM)));
                    setGraphic(null);
                }
            }
        }

        private void createDatePicker() {
            TimePicker = new JFXTimePicker(getTime());
            TimePicker.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
            TimePicker.setOnAction((e) -> {
                System.out.println("Committed: " + TimePicker.getValue().toString());
                commitEdit(Time.valueOf(TimePicker.getValue()));
            });
//            datePicker.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
//                if (!newValue) {
//                    commitEdit(Date.from(datePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
//                }
//            });
        }

        private LocalTime getTime() {
            return getItem() == null ? LocalTime.now() : getItem().toLocalTime();
        }
    }
