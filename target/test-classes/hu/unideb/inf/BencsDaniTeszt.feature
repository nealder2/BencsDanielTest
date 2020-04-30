Feature: YourLogo login process

  Background:
    Given The home page is opened
    And The Sign In Link clicked

  Scenario:
    Given The Sign In button is clicked
    Then An email address required error message is shown

  Scenario Outline:
    Given The '<field>' is filled with '<value>'
    And The Sign In button is clicked
    Then An '<msg>' error is shown
    Examples:
      | field | value             | msg                    |
      | email | invalid.email.com | Invalid email address. |
      | email | valid@email.com   | Password is required.  |

  Scenario:
    Given The credentials are filled correctly
    And The Sign In button is clicked
    And Login is successful
    And Sign Out button is clicked
    Then Login Page is shown

  Scenario Outline:
    Given The credentials are filled correctly
    And The Sign In button is clicked
    And Login is successful
    And The '<btn>' button is clicked
    And The '<content>' is shown
    Examples:
      | btn           | content                   |
      | Orders        | ORDER HISTORY             |
      | Credit slips  | CREDIT SLIPS              |
      | Addresses     | MY ADDRESSES              |

  Scenario Outline:
    Given The credentials are filled correctly
    And The Sign In button is clicked
    And Login is successful
    And The '<btn>' button is clicked
    And The other '<content>' is shown
    Examples:
      | btn           | content                   |
      | My wishlists  | MY WISHLISTS              |
      | Information   | YOUR PERSONAL INFORMATION |

  Scenario:
    Given An empty search string
    Then Zero result page is shown

  Scenario:
    Given Actual search term entered
    And The search result is found
    And The search result is clicked
    Then The item is shown

  Scenario:
    Given The credentials are filled correctly
    And The Sign In button is clicked
    And Login is successful
    And Actual search term entered
    And The search result is found
    And The search result is clicked
    Then The item is shown
    Then Add item to wish
    Then Click account
    Then Click wishlist
    Then Check item in wish
    Then Delete item from wish

  Scenario:
    Given Contact us is clicked
    Then Contact form appears

  Scenario:
    Given Home navigation button clicked

  Scenario:
    Given Forgott password clicked