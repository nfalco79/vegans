package it.nfalco79.example.api.dto;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The data object that represent a polygon, a flat geometric figure delimited
 * by a closed broken line (segments).
 *
 * @author NikolasFalco
 */
@XmlRootElement
@Polygon
public class PolygonDTO {
    private String name;
    private List<Double> segments = new LinkedList<>();

    /**
     * Default constructor.
     */
    public PolygonDTO() {
    }

    /**
     * Copy constructor.
     *
     * @param other from copy too
     */
    public PolygonDTO(PolygonDTO other) {
        if (other == null) {
            throw new IllegalArgumentException("other is null");
        }

        this.name = other.name;
        if (other.segments != null) {
            this.segments = new LinkedList<>(other.segments);
        }
    }

    @NotEmpty
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Size(min = 1)
    public List<@NotNull Double> getSegments() {
        return segments;
    }

    public void setSegments(List<Double> segments) {
        this.segments = segments != null ? new LinkedList<>(segments) : new LinkedList<>();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, segments);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        PolygonDTO other = (PolygonDTO) obj;
        return Objects.equals(name, other.name) //
                && Objects.equals(segments, other.segments);
    }

}
