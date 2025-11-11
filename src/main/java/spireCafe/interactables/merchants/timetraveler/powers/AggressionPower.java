package spireCafe.interactables.merchants.timetraveler.powers;

import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import spireCafe.abstracts.AbstractSCPower;

import static spireCafe.Anniv7Mod.makeID;

public class AggressionPower extends AbstractSCPower {
    public static String POWER_ID = makeID(AggressionPower.class.getSimpleName());
    public static PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static String[] DESCRIPTIONS = strings.DESCRIPTIONS;

    public AggressionPower(AbstractCreature owner, int amount) {
        super(POWER_ID, strings.NAME, "TimeTravelerMerchant", PowerType.BUFF, false, owner, amount);
    }

    public void atStartOfTurn() {
        addToBot(new DiscardToHandAction(target));
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }
}
