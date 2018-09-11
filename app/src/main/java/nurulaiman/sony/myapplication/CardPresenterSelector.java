package nurulaiman.sony.myapplication;

import android.content.Context;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.PresenterSelector;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

public class CardPresenterSelector extends PresenterSelector {

    private final Context mContext;
    //private final HashMap<Card.Type, Presenter> presenters = new HashMap<Card.Type, Presenter>();
    private final HashMap<Map.Entry<Card.Type,String>, Presenter> presenters = new HashMap<Map.Entry<Card.Type,String>, Presenter>();


    public CardPresenterSelector(Context context) {
        mContext = context;
    }

    @Override
    public Presenter getPresenter(Object item) {
        if (!(item instanceof Card)) throw new RuntimeException(
                String.format("The PresenterSelector only supports data items of type '%s'",
                        Card.class.getName()));
        Card card = (Card) item;
        Presenter presenter = presenters.get(card.getType());
        if (presenter == null) {
            //presenter = new ImageCardViewPresenter(mContext, card.getType());
            presenter = new ImageCardViewPresenter(mContext,card.getType() ,card.getVideoId());
        }

        //presenters.put(card.getType(), presenter);

        presenters.put(new AbstractMap.SimpleEntry(card.getType(),card.getVideoId()), presenter);

        return presenter;
    }

}
