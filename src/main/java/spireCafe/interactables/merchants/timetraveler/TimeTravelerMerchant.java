package spireCafe.interactables.merchants.timetraveler;

import basemod.ReflectionHacks;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.blue.Claw;
import com.megacrit.cardcrawl.cards.colorless.PanicButton;
import com.megacrit.cardcrawl.cards.colorless.SecretWeapon;
import com.megacrit.cardcrawl.cards.colorless.ThinkingAhead;
import com.megacrit.cardcrawl.cards.green.BladeDance;
import com.megacrit.cardcrawl.cards.green.GrandFinale;
import com.megacrit.cardcrawl.cards.purple.Scrawl;
import com.megacrit.cardcrawl.cards.red.*;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import spireCafe.Anniv7Mod;
import spireCafe.abstracts.AbstractMerchant;
import spireCafe.interactables.merchants.CardArticle;
import spireCafe.interactables.merchants.fleamerchant.HaggleArticle;
import spireCafe.util.TexLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class TimeTravelerMerchant extends AbstractMerchant {
    public static final String ID = TimeTravelerMerchant.class.getSimpleName();
    public static final CharacterStrings timeTravelerStrings = CardCrawlGame.languagePack.getCharacterString(Anniv7Mod.makeID(ID));
    private static final Texture BG_TEXTURE = ImageMaster.loadImage("images/npcs/rug/eng.png");

    private static final float TOP_ROW_Y = 760.0F * Settings.yScale;
    private static final float BOTTOM_ROW_Y = 337.0F * Settings.yScale;
    private static final float DRAW_START_X = Settings.WIDTH * 0.16F;

    private float speechTimer;

    public TimeTravelerMerchant(float animationX, float animationY) {
        super(animationX, animationY, 360.0f, 235.0f);
        this.name = timeTravelerStrings.NAMES[0];
        this.img = TexLoader.getTexture(Anniv7Mod.makeMerchantPath("timetravelermerchant/merchant.png"));
        background = new TextureRegion(BG_TEXTURE);
        speechTimer = 1.5F;
        this.facingDirection = FacingDirection.FORWARD;
    }

    @Override
    public void onInteract() {
        super.onInteract();
        this.speechTimer = MathUtils.random(5.0f, 10.0f);
    }

    @Override
    public void rollShop() {
        ArrayList<AbstractCard> cards = new ArrayList<>();
        // V Existing cards that are seen in trailers, neowsletters or otherwise confirmed to be coming back
        cards.add(new Claw());
        cards.add(new ThinkingAhead()); // New art, Uncommon, Hand and Draw Pile keyworded
        cards.add(new ThunderClap()); // New art
        cards.add(new PanicButton()); // New art
        cards.add(new SecretWeapon()); // New art, Hand keyworded
        cards.add(new PerfectedStrike());
        cards.add(new ShrugItOff());
        cards.add(new BloodForBlood()); // New art, text order flipped
        cards.add(new Anger()); // New art, Discard Pile keyworded
        cards.add(new Scrawl()); // New art, colorless
        cards.add(new BladeDance()); // New art, Exhausts, Hand keyworded
        cards.add(new TrueGrit()); // New art, wording now "Exhaust 1 card at random"
        cards.add(new IronWave()); // New art
        cards.add(new Rampage()); // New art, +1 damage
        cards.add(new GrandFinale());
        /* V New cards that are seen in trailers, neowsletters or otherwise at some points revealed, even if confirmed to be changed again
        cards.add(new GoldAxe());
        cards.add(new Fasten());
        cards.add(new Bully());
        cards.add(new Aggression());
        cards.add(new Cinder());
        cards.add(new Scourge());
        cards.add(new Unrelenting());
        cards.add(new BloodWall());
        cards.add(new Tremble());
        cards.add(new StoneArmor());
        cards.add(new Breakthrough());
        cards.add(new CarveGhost());
        cards.add(new Putrefy());
        cards.add(new Swipe());
        cards.add(new Friendship());
        cards.add(new NegativePulse());
        cards.add(new Bully());
        cards.add(new Fearmonger());
        cards.add(new TimesUp());
        cards.add(new GraveWarden());
        cards.add(new DrainPower());
        cards.add(new PullAggro());
        cards.add(new Oblivion());
        cards.add(new DeathsVisage());
        cards.add(new FanOfKnifes());
        cards.add(new Hellraiser());
        cards.add(new BlightStrike());
        cards.add(new Bodyguard());
        cards.add(new BoneShards());
        cards.add(new Neurosurge());
        cards.add(new GraveWarden2());
        cards.add(new SculptingStrike());
        cards.add(new Transfigure());
        cards.add(new ThrummingHatchet());
        cards.add(new RollingBoulder());
        cards.add(new Automation());
        cards.add(new Flick-Flack());
        cards.add(new MasterPlanner());
        cards.add(new Cruelty());
        cards.add(new Charge());
         */

        int tmp = (int) (Settings.WIDTH - DRAW_START_X * 2.0F - AbstractCard.IMG_WIDTH_S * 5.0F) / 4;
        float padX = (int) (tmp + AbstractCard.IMG_WIDTH_S) + 10.0F * Settings.scale;
        for (int i = 0; i < Math.min(cards.size(), 10); i++) {
            float xPos = DRAW_START_X + AbstractCard.IMG_WIDTH_S / 2.0F + padX * (i % 5);
            float yPos = i < 5 ? TOP_ROW_Y : BOTTOM_ROW_Y;
            AbstractCard card = cards.get(i);
            int price = (int) (AbstractCard.getPrice(card.rarity) * AbstractDungeon.miscRng.random(0.9f, 1.1f));
            articles.add(new CardArticle(card.cardID, this, xPos, yPos, card.makeCopy(), price));
        }
    }

    @Override
    public void updateShop() {
        super.updateShop();
        this.speechTimer -= Gdx.graphics.getDeltaTime();
        if (this.speechBubble == null && this.speechTimer <= 0.0f) {
            this.speechTimer = MathUtils.random(40.0f, 60.0f);
            this.createSpeechBubble(this.getRandomMessage());
        }
    }

    private String getRandomMessage() {
        String[] s = timeTravelerStrings.TEXT;
        ArrayList<String> possibleMessages = new ArrayList<>();
        int baseMessageCount = 6;
        for (int i = 0; i < baseMessageCount; i++) {
            possibleMessages.add(s[i]);
        }
        return possibleMessages.get(MathUtils.random(possibleMessages.size() - 1));
    }
}
