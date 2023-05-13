$(document).ready(() => {
    const PLAYER1 = "player1";
    const PLAYER2 = "player2";
    const ENABLED_CLASS = "enabled";
    const DISABLED_CLASS = "disabled";
  
    loadGame(false);
  
    $(".pit").click(function () {
      if ($(this).hasClass(DISABLED_CLASS)) return;
  
      const { gameId } = localStorage;
      const player = $(this).parent().attr('id');
      const pitNumber = $(this).index() - 1;
  
      $.ajax({
        url: "/move",
        type: "POST",
        data: JSON.stringify({ gameId, player, pitNumber }),
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        success: drawBoard
      });
    });
  
    $("#newGame").click(() => loadGame(true));
  
    function loadGame(isNewGame = false) {
      $("#winner").hide();
      const { gameId } = localStorage;
  
      $.ajax({
        url: "/start",
        data: { gameId },
        success: drawBoard
      });
    }
  
    function drawBoard(result) {
      const { gameId, player1, player2, currentPlayer, isWinnerExist, winner } = result;
      localStorage.setItem('gameId', gameId);
  
      drawPits(player1, PLAYER1);
      drawPits(player2, PLAYER2);
  
      $("#player1 #player1-mancala > span").html(player1.mancala);
      $("#player2 #player2-mancala > span").html(player2.mancala);
  
      const isEnabled = currentPlayer === PLAYER1 ? ENABLED_CLASS : DISABLED_CLASS;
      const isDisabled = currentPlayer === PLAYER2 ? ENABLED_CLASS : DISABLED_CLASS;
  
      $(`#${PLAYER1} .pit`).removeClass(isDisabled).addClass(isEnabled);
      $(`#${PLAYER2} .pit`).removeClass(isEnabled).addClass(isDisabled);
  
  
      if (isWinnerExist) {
        $("#player1 div, #player2 div").removeClass(ENABLED_CLASS).addClass(DISABLED_CLASS);
        $('#winner').show().children("span").html(winner);
      }
    }
  
    function drawPits(player, playerId) {
      player.pits.forEach((pit, index) => {
        $(`#${playerId} .pit`).eq(index).html(pit);
      });
    }
  });
  