package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.risklevel.RiskLevel;
import seedu.address.model.student.Address;
import seedu.address.model.student.Name;
import seedu.address.model.student.Note;
import seedu.address.model.student.Phone;
import seedu.address.model.student.Student;
import seedu.address.model.util.NameFormatter;

/**
 * Jackson-friendly version of {@link Student}.
 */
class JsonAdaptedStudent {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Student's %s field is missing!";
    public static final String EXCEED_RISK_LEVEL_SIZE_MESSAGE = "Student has more than one risk levels!";

    private final String name;
    private final String phone;
    private final String address;
    private final List<JsonAdaptedRiskLevel> tags = new ArrayList<>();
    private final String note;

    /**
     * Constructs a {@code JsonAdaptedStudent} with the given student details.
     */
    @JsonCreator
    public JsonAdaptedStudent(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                              @JsonProperty("address") String address,
                              @JsonProperty("tags") List<JsonAdaptedRiskLevel> tags,
                              @JsonProperty("note") String note) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        if (tags != null) {
            this.tags.addAll(tags);
        }
        this.note = note;
    }

    /**
     * Converts a given {@code Student} into this class for Jackson use.
     */
    public JsonAdaptedStudent(Student source) {
        name = source.getName().value;
        phone = source.getPhone().value;
        address = source.getAddress().value;
        tags.addAll(source.getRiskLevel().stream()
                .map(JsonAdaptedRiskLevel::new)
                .collect(Collectors.toList()));
        note = source.getNote().value;
    }

    /**
     * Converts this Jackson-friendly adapted student object into the model's {@code Student} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted student.
     */
    public Student toModelType() throws IllegalValueException {
        final List<RiskLevel> studentRiskLevel = new ArrayList<>();


        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        String capitalisedName = NameFormatter.format(name);
        if (!Name.isValidName(capitalisedName)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(capitalisedName);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }

        if (note == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Note.class.getSimpleName()));
        }

        if (!Note.isValidNote(note)) {
            throw new IllegalValueException(Note.MESSAGE_CONSTRAINTS);
        }

        if (tags.size() > 1) {
            throw new IllegalValueException(EXCEED_RISK_LEVEL_SIZE_MESSAGE);
        }

        assert tags != null;

        for (JsonAdaptedRiskLevel riskLevel : tags) {
            studentRiskLevel.add(riskLevel.toModelType());
        }

        final Note modelNote = new Note(note);

        final Address modelAddress = new Address(address);
        final Set<RiskLevel> modelTags = new HashSet<>(studentRiskLevel);
        return new Student(modelName, modelPhone, modelAddress, modelTags, modelNote);
    }

}
