import { PrismaClient } from '@prisma/client';

const prisma = new PrismaClient();

const tags = [
  { name: '📖 Novel' },
  { name: '📝 Essay' },
  { name: '📚 Short Story' },
  { name: '🎭 Theater' },
  { name: '🏛️ Historical' },
  { name: '📖 Biography' },
  { name: '🖋️ Poetry' },
  { name: '🕵️ Mystery' },
  { name: '🐻 Polar' },
  { name: '🎭 Drama' },
  { name: '⚔️ Action' },
  { name: '😂 Comedy' },
  { name: '😢 Tragedy' },
  { name: '🌍 Adventure' },
  { name: '✨ Fantasy' },
  { name: '🤖 Science-Fiction' },
  { name: '🔪 Thriller' },
  { name: '👻 Horror' },
  { name: '💕 Romance' },
  { name: '👶 Children' },
  { name: '📰 Comics' },
  { name: '📚 Manga' },
  { name: '🍳 Cooking' },
  { name: '✈️ Travel' },
  { name: '🎨 Art' },
  { name: '⛪ Religion' },
  { name: '🔬 Science' },
  { name: '💻 Computer' },
  { name: '💼 Business' },
  { name: '⚽ Sport' },
  { name: '🎵 Music' }
];


const authors = [
  {
    firstname: "William",
    lastname: "Shakespeare",
    books: {
      create: [
        {
          title: "Romeo and Juliet",
          publication_year: 1597,
          tags: {
            connect: [
              { name: "🎭 Drama" },
              { name: "💕 Romance" },
              { name: "😢 Tragedy" }
            ]
          }
        },
        {
          title: "Hamlet",
          publication_year: 1603,
          tags: {
            connect: [
              { name: "🎭 Drama" },
              { name: "😢 Tragedy" }
            ]
          }
        }
      ]
    }
  },
  {
    firstname: "Charles",
    lastname: "Dickens",
    books: {
      create: [
        {
          title: "Oliver Twist",
          publication_year: 1837,
          tags: {
            connect: [
              { name: "📖 Novel" },
              { name: "🏛️ Historical" },
              { name: "🌍 Adventure" }
            ]
          }
        },
        {
          title: "Great Expectations",
          publication_year: 1861,
          tags: {
            connect: [
              { name: "📖 Novel" },
              { name: "🏛️ Historical" },
              { name: "🌍 Adventure" }
            ]
          }
        }
      ]
    }
  },
  {
    firstname: "J. R. R.",
    lastname: "Tolkien",
    books: {
      create: [
        {
          title: "The Hobbit",
          publication_year: 1937,
          tags: {
            connect: [
              { name: "📖 Novel" },
              { name: "🌍 Adventure" },
              { name: "✨ Fantasy" }
            ]
          }
        },
        {
          title: "The Lord of the Rings",
          publication_year: 1954,
          tags: {
            connect: [
              { name: "📖 Novel" },
              { name: "🌍 Adventure" },
              { name: "✨ Fantasy" }
            ]
          }
        }
      ]
    }
  },
  {
    firstname: "Antoine de",
    lastname: "Saint-Exupéry",
    books: {
      create: [
        {
          title: "Le Petit Prince",
          publication_year: 1943,
          tags: {
            connect: [
              { name: "📖 Novel" },
              { name: "📚 Short Story" },
              { name: "🌍 Adventure" },
              { name: "✨ Fantasy" }
            ]
          }
        }
      ]
    }
  },
  {
    firstname: "Jules",
    lastname: "Verne",
    books: {
      create: [
        {
          title: "Vingt mille lieues sous les mers",
          publication_year: 1870,
          tags: {
            connect: [
              { name: "📖 Novel" },
              { name: "🌍 Adventure" },
              { name: "🤖 Science-Fiction" }
            ]
          }
        },
        {
          title: "Le Tour du monde en quatre-vingts jours",
          publication_year: 1873,
          tags: {
            connect: [
              { name: "📖 Novel" },
              { name: "🌍 Adventure" }
            ]
          }
        }
      ]
    }
  },
  {
    firstname: "Agatha",
    lastname: "Christie"
  },
  {
    firstname: "Stephen",
    lastname: "King",
    books: {
      create: [
        {
          title: "It",
          publication_year: 1986,
          tags: {
            connect: [
              { name: "📖 Novel" },
              { name: "👻 Horror" }
            ]
          }
        },
        {
          title: "The Shining",
          publication_year: 1977,
          tags: {
            connect: [
              { name: "📖 Novel" },
              { name: "👻 Horror" }
            ]
          }
        }
      ]
    }
  },
  {
    firstname: "J. K.",
    lastname: "Rowling",
    books: {
      create: [
        {
          title: "Harry Potter and the Philosopher's Stone",
          publication_year: 1997,
          tags: {
            connect: [
              { name: "📖 Novel" },
              { name: "🌍 Adventure" },
              { name: "✨ Fantasy" }
            ]
          }
        }
      ]
    }
  },
  {
    firstname: "George R. R.",
    lastname: "Martin",
    books: {
      create: [
        {
          title: "A Game of Thrones",
          publication_year: 1996,
          tags: {
            connect: [
              { name: "📖 Novel" },
              { name: "🌍 Adventure" },
              { name: "✨ Fantasy" }
            ]
          }
        }
      ]
    }
  },
  {
    firstname: "Victor",
    lastname: "Hugo",
    books: {
      create: [
        {
          title: "Les Misérables",
          publication_year: 1862,
          tags: {
            connect: [
              { name: "📖 Novel" },
              { name: "🏛️ Historical" },
              { name: "🌍 Adventure" }
            ]
          }
        }
      ]
    }
  },
  {
    firstname: "Alexandre",
    lastname: "Dumas",
    books: {
      create: [
        {
          title: "Les Trois Mousquetaires",
          publication_year: 1844,
          tags: {
            connect: [
              { name: "📖 Novel" },
              { name: "🏛️ Historical" },
              { name: "🌍 Adventure" }
            ]
          }
        }
      ]
    }
  },
  {
    firstname: "Arthur",
    lastname: "Conan Doyle",
    books: {
      create: [
        {
          title: "A Study in Scarlet",
          publication_year: 1887,
          tags: {
            connect: [
              { name: "📖 Novel" },
              { name: "🕵️ Mystery" }
            ]
          }
        },
        {
          title: "The Hound of the Baskervilles",
          publication_year: 1902,
          tags: {
            connect: [
              { name: "📖 Novel" },
              { name: "🕵️ Mystery" }
            ]
          }
        }
      ]
    }
  },
  {
    firstname: "Oscar",
    lastname: "Wilde",
    books: {
      create: [
        {
          title: "The Picture of Dorian Gray",
          publication_year: 1890,
          tags: {
            connect: [
              { name: "📖 Novel" },
              { name: "🕵️ Mystery" }
            ]
          }
        }
      ]
    }
  },
  {
    firstname: "Jane",
    lastname: "Austen",
    books: {
      create: [
        {
          title: "Pride and Prejudice",
          publication_year: 1813,
          tags: {
            connect: [
              { name: "📖 Novel" },
              { name: "💕 Romance" }
            ]
          }
        }
      ]
    }
  },
  {
    firstname: "Mark",
    lastname: "Twain",
    books: {
      create: [
        {
          title: "The Adventures of Tom Sawyer",
          publication_year: 1876,
          tags: {
            connect: [
              { name: "📖 Novel" },
              { name: "🌍 Adventure" }
            ]
          }
        },
        {
          title: "Adventures of Huckleberry Finn",
          publication_year: 1884,
          tags: {
            connect: [
              { name: "📖 Novel" },
              { name: "🌍 Adventure" }
            ]
          }
        }
      ]
    }
  },
  {
    firstname: "Ernest",
    lastname: "Hemingway",
  },
  {
    firstname: "F. Scott",
    lastname: "Fitzgerald"
  },
  {
    firstname: "H. G.",
    lastname: "Wells",
    books: {
      create: [
        {
          title: "The War of the Worlds",
          publication_year: 1898,
          tags: {
            connect: [
              { name: "📖 Novel" },
              { name: "🤖 Science-Fiction" },
              { name: "🌍 Adventure" }
            ]
          }
        },
        {
          title: "The Time Machine",
          publication_year: 1895,
          tags: {
            connect: [
              { name: "📖 Novel" },
              { name: "🤖 Science-Fiction" },
              { name: "🌍 Adventure" }
            ]
          }
        }
      ]
    }
  },
  {
    firstname: "Mary",
    lastname: "Shelley",
    books: {
      create: [
        {
          title: "Frankenstein",
          publication_year: 1818,
          tags: {
            connect: [
              { name: "📖 Novel" },
              { name: "👻 Horror" }
            ]
          }
        }
      ]
    }
  },
  {
    firstname: "Edgar Allan",
    lastname: "Poe"
  },
  {
    firstname: "Honoré de",
    lastname: "Balzac",
    books: {
      create: [
        {
          title: "Le Père Goriot",
          publication_year: 1835,
          tags: {
            connect: [
              { name: "📖 Novel" },
              { name: "🏛️ Historical" }
            ]
          }
        }
      ]
    }
  },
  {
    firstname: "Gustave",
    lastname: "Flaubert"
  },
  {
    firstname: "Marcel",
    lastname: "Proust",
    books: {
      create: [
        {
          title: "À la recherche du temps perdu",
          publication_year: 1913,
          tags: {
            connect: [
              { name: "📖 Novel" },
              { name: "🌍 Adventure" }
            ]
          }
        }
      ]
    }
  },
  {
    firstname: "Franz",
    lastname: "Kafka",
    books: {
      create: [
        {
          title: "The Metamorphosis",
          publication_year: 1915,
          tags: {
            connect: [
              { name: "📖 Novel" },
              { name: "📚 Short Story" },
              { name: "🕵️ Mystery" }
            ]
          }
        }
      ]
    }
  },
  {
    firstname: "Fiodor",
    lastname: "Dostoïevski"
  },
  {
    firstname: "Léon",
    lastname: "Tolstoï"
  },
  {
    firstname: "Miguel de",
    lastname: "Cervantès"
  },
  {
    firstname: "Isaac",
    lastname: "Asimov",
    books: {
      create: [
        {
          title: "Foundation",
          publication_year: 1951,
          tags: {
            connect: [
              { name: "📖 Novel" },
              { name: "🤖 Science-Fiction" },
              { name: "🌍 Adventure" }
            ]
          }
        },
        {
          title: "I, Robot",
          publication_year: 1950,
          tags: {
            connect: [
              { name: "📚 Short Story" },
              { name: "🤖 Science-Fiction" },
              { name: "🌍 Adventure" }
            ]
          }
        }
      ]
    }
  },
  {
    firstname: "George",
    lastname: "Orwell",
    books: {
      create: [
        {
          title: "1984",
          publication_year: 1949,
          tags: {
            connect: [
              { name: "📖 Novel" },
              { name: "🤖 Science-Fiction" },
              { name: "🌍 Adventure" }
            ]
          }
        },
        {
          title: "Animal Farm",
          publication_year: 1945,
          tags: {
            connect: [
              { name: "📖 Novel" },
              { name: "🌍 Adventure" }
            ]
          }
        }
      ]
    }
  },
  {
    firstname: "Lewis",
    lastname: "Carroll",
    books: {
      create: [
        {
          title: "Alice's Adventures in Wonderland",
          publication_year: 1865,
          tags: {
            connect: [
              { name: "📖 Novel" },
              { name: "🌍 Adventure" },
              { name: "✨ Fantasy" }
            ]
          }
        }
      ]
    }
  },
  {
    firstname: "Roald",
    lastname: "Dahl",
    books: {
      create: [
        {
          title: "Charlie and the Chocolate Factory",
          publication_year: 1964,
          tags: {
            connect: [
              { name: "📖 Novel" },
              { name: "🌍 Adventure" },
              { name: "👶 Children" }
            ]
          }
        }
      ]
    }
  },
  {
    firstname: "H. P.",
    lastname: "Lovecraft",
    books: {
      create: [
        {
          title: "The Call of Cthulhu",
          publication_year: 1928,
          tags: {
            connect: [
              { name: "📚 Short Story" },
              { name: "👻 Horror" }
            ]
          }
        }
      ]
    }
  },
  {
    firstname: "Rudyard",
    lastname: "Kipling"
  },
  {
    firstname: "Robert Louis",
    lastname: "Stevenson",
    books: {
      create: [
        {
          title: "Treasure Island",
          publication_year: 1883,
          tags: {
            connect: [
              { name: "📖 Novel" },
              { name: "🌍 Adventure" }
            ]
          }
        }
      ]
    }
  },
  {
    firstname: "Ian",
    lastname: "Fleming"
  },
  {
    firstname: "Arthur C.",
    lastname: "Clarke",
    books: {
      create: [
        {
          title: "2001: A Space Odyssey",
          publication_year: 1968,
          tags: {
            connect: [
              { name: "📖 Novel" },
              { name: "🤖 Science-Fiction" },
              { name: "🌍 Adventure" }
            ]
          }
        }
      ]
    }
  },
  {
    firstname: "Ray",
    lastname: "Bradbury",
    books: {
      create: [
        {
          title: "Fahrenheit 451",
          publication_year: 1953,
          tags: {
            connect: [
              { name: "📖 Novel" },
              { name: "🤖 Science-Fiction" },
              { name: "🌍 Adventure" }
            ]
          }
        }
      ]
    }
  },
  {
    firstname: "Frank",
    lastname: "Herbert",
    books: {
      create: [
        {
          title: "Dune",
          publication_year: 1965,
          tags: {
            connect: [
              { name: "📖 Novel" },
              { name: "🤖 Science-Fiction" },
              { name: "🌍 Adventure" }
            ]
          }
        }
      ]
    }
  },
  {
    firstname: "Dan",
    lastname: "Simmons",
    books: {
      create: [
        {
          title: "Hyperion",
          publication_year: 1989,
          tags: {
            connect: [
              { name: "📖 Novel" },
              { name: "🤖 Science-Fiction" },
              { name: "🌍 Adventure" }
            ]
          }
        }
      ]
    }
  },
  {
    firstname: "Douglas",
    lastname: "Adams",
    books: {
      create: [
        {
          title: "The Hitchhiker's Guide to the Galaxy",
          publication_year: 1979,
          tags: {
            connect: [
              { name: "📖 Novel" },
              { name: "🤖 Science-Fiction" },
              { name: "🌍 Adventure" },
              { name: "😂 Comedy" }
            ]
          }
        }
      ]
    }
  },
  {
    firstname: "Albert",
    lastname: "Camus",
    books: {
      create: [
        {
          title: "L'Étranger",
          publication_year: 1942,
          tags: {
            connect: [
              { name: "📖 Novel" },
              { name: "🕵️ Mystery" }
            ]
          }
        }
      ]
    }
  },
  {
    firstname: "Emile",
    lastname: "Zola",
    books: {
      create: [
        {
          title: "Germinal",
          publication_year: 1885,
          tags: {
            connect: [
              { name: "📖 Novel" },
              { name: "🏛️ Historical" }
            ]
          }
        }
      ]
    }
  },
  {
    firstname: "Charles",
    lastname: "Baudelaire",
    books: {
      create: [
        {
          title: "Les Fleurs du mal",
          publication_year: 1857,
          tags: {
            connect: [
              { name: "🖋️ Poetry" }
            ]
          }
        }
      ]
    }
  },
  {
    firstname: "Guy de",
    lastname: "Maupassant"
  }
];

async function main() {
  for (const tag of tags) {
    await prisma.tag.create({
      data: tag
    });
  }
  for (const author of authors) {
    await prisma.author.create({
      data: author
    });
  }
}

main()
  .then(async () => {
    await prisma.$disconnect();
  })
  .catch(async (e) => {
    console.error(e);
    await prisma.$disconnect();
    process.exit(1);
  });
