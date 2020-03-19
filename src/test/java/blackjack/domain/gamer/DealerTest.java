package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardSymbol;
import blackjack.domain.card.CardType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class DealerTest {

    private Dealer dealer;

    @BeforeEach
    void setUp() {
        dealer = new Dealer();
    }

    @Test
    @DisplayName("딜러의 getName은 딜러를 반환")
    void getDealerName() {
        assertThat(dealer.getName()).isEqualTo("딜러");
    }

    @Test
    @DisplayName("dealer는 첫번째 카드만 내보내는 메서드를 가짐")
    void getDealerFirstHand() {
        Card aceSpade = new Card(CardSymbol.ACE, CardType.SPADE);
        Card twoHeart = new Card(CardSymbol.TWO, CardType.HEART);
        dealer.draw(aceSpade);
        dealer.draw(twoHeart);

        assertThat(dealer.getInitialHand())
                .contains(aceSpade)
                .doesNotContain(twoHeart);
    }

    @ParameterizedTest
    @MethodSource("createCards")
    @DisplayName("딜러가 갖고 있는 카드의 합이 16 이하인지 확인")
    void shouldReceiveCard1(List<Card> cards, boolean shouldDrawCard) {
        for (Card card : cards) {
            dealer.draw(card);
        }
        assertThat(dealer.shouldDrawCard()).isEqualTo(shouldDrawCard);
    }

    private static Stream<Arguments> createCards() {
        Card aceSpade = new Card(CardSymbol.ACE, CardType.SPADE);
        Card queenClover = new Card(CardSymbol.QUEEN, CardType.CLOVER);
        Card kingClover = new Card(CardSymbol.KING, CardType.CLOVER);
        List<Card> cardSumUnderSixteen = Arrays.asList(aceSpade);
        List<Card> cardSumOverSixteen = Arrays.asList(aceSpade, queenClover, kingClover);
        return Stream.of(
                Arguments.of(cardSumUnderSixteen, true),
                Arguments.of(cardSumOverSixteen, false));
    }
}