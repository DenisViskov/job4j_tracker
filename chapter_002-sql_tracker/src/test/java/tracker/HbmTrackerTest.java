package tracker;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class HbmTrackerTest {

    @Test
    public void addTest() {
        HbmTracker tracker = new HbmTracker();
        ItemExample item = new ItemExample("new Item");
        ItemExample out = tracker.add(item);
        assertThat(out, is(item));
    }

    @Test
    public void replaceTest() {
        HbmTracker tracker = new HbmTracker();
        ItemExample item = new ItemExample("new Item");
        ItemExample tmp = tracker.add(item);
        tmp.setName("new name");
        tracker.replace(tmp.getId(), tmp);
        ItemExample out = tracker.findById(tmp.getId());
        assertThat(out.getName(), is(tmp.getName()));
    }

    @Test
    public void deleteTest() {
        HbmTracker tracker = new HbmTracker();
        ItemExample item = new ItemExample("new Item");
        ItemExample out = tracker.add(item);
        tracker.delete(out.getId());
        out = tracker.findById(out.getId());
        assertNull(out);
    }

    @Test
    public void findAllTest() {
        HbmTracker tracker = new HbmTracker();
        ItemExample item = new ItemExample("new Item");
        ItemExample out = tracker.add(item);
        List<ItemExample> all = tracker.findAll();
        assertThat(all.size(), is(1));
    }

    @Test
    public void findByNameTest() {
        HbmTracker tracker = new HbmTracker();
        ItemExample item = new ItemExample("new Item");
        tracker.add(item);
        List<ItemExample> out = tracker.findByName("new Item");
        assertThat(out.get(0).getName(), is("new Item"));
    }
}