package constantmultiplier;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.Duration;

public class ConstantMultiplierController {
    @FXML private TextField numbersQuantity, seed, constant;
    @FXML private TextArea numberList;
    @FXML private Label errors;
    @FXML private Button generate;

    @FXML
    private void generateRandomNumbers(ActionEvent event) {
        generate.setDisable(true);
        try {
            algorithmPropertiesValidation(numbersQuantity.getText(), seed.getText(), constant.getText());
            var constantMultiplierAlgorithm = new ConstantMultiplierAlgorithm(
                    Integer.parseInt(numbersQuantity.getText()),
                    Integer.parseInt(seed.getText()),
                    Integer.parseInt(constant.getText())
            );
            var numbers = constantMultiplierAlgorithm.generateNumbers();
            String numberListString = "";
            for (var number: numbers) {
                numberListString += number + " ";
            }
            numberListString = numberListString.trim();
            numberListString = numberListString.replace(" ", "\n");
            numberList.setText(numberListString);
            generate.setDisable(false);
        } catch (NumberFormatException e) {
            errors.setText("Check the fields, just enter whole numbers");
        } catch (ConstantMultiplierException e) {
            errors.setText(e.getMessage());
        } if (!errors.getText().isEmpty()) {
            numberList.setText("");
            new Timeline(new KeyFrame(
                    Duration.seconds(3),
                    fx -> {
                        errors.setText("");
                        generate.setDisable(false);
                    }
            )).play();
        }
    }

    private void algorithmPropertiesValidation (String numberQuantity, String seed, String constant) throws ConstantMultiplierException {
        emptyValidation(numberQuantity, seed, constant);
        lengthValidation(seed, constant);
    }

    private void emptyValidation (String numberQuantity, String seed, String constant) throws ConstantMultiplierException {
        if (numberQuantity.isEmpty() && seed.isEmpty() && constant.isEmpty()) {
            throw new ConstantMultiplierException("The fields can't be empty");
        } else if (numberQuantity.isEmpty() && seed.isEmpty()) {
            throw new ConstantMultiplierException("The fields quantity of numbers and the seed can't be empty");
        } else if (numberQuantity.isEmpty() && constant.isEmpty()) {
            throw new ConstantMultiplierException("The fields quantity of numbers and the constant can't be empty");
        } else if (seed.isEmpty() && constant.isEmpty()) {
            throw new ConstantMultiplierException("The seed and constant fields can't be empty");
        } else if (numberQuantity.isEmpty()) {
            throw new ConstantMultiplierException("The field quantity of numbers can't be empty");
        } else if (seed.isEmpty()) {
            throw new ConstantMultiplierException("The seed field can't be empty");
        } else if (constant.isEmpty()) {
            throw new ConstantMultiplierException("The constant field can't be empty");
        }
    }

    private void lengthValidation (String seed, String constant) throws ConstantMultiplierException {
        if (seed.length() <= 3 && constant.length() <= 3) {
            throw new ConstantMultiplierException("The length of the seed and the constant must be greater than 3 digits");
        } else if (seed.length() <= 3) {
            throw new ConstantMultiplierException("The length of the seed must be greater than 3 digits");
        } else if (constant.length() <= 3) {
            throw new ConstantMultiplierException("The length of the constant must be greater than 3 digits");
        }
    }
}

